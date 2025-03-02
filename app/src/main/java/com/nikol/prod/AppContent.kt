package com.nikol.prod

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nikol.navigation.BottomBarItem
import com.nikol.navigation.FeatureApi
import com.nikol.prod.nav.AppNavGraph

val focusedBorderColor = Color(0xFF7A5AF8)
val unfocusedBorderColor = Color(0xFFCCCCCC)
val errorBorderColor = Color(0xFFE53935)
val textColor = Color(0xFF333333)
val backgroundColor = Color.White

@Composable
fun AppContent(
    bottomBarItems: List<BottomBarItem>,
    featureNavigationApis: List<FeatureApi>,
    authStateViewModel: AuthStateViewModel = hiltViewModel(),
) {
    val navController = rememberNavController()
    val authState by authStateViewModel.authState.collectAsState()

    when (authState) {
        is AuthState.Initial, is AuthState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is AuthState.Authenticated -> {
            val currentDestinationRoute =
                navController.currentBackStackEntryAsState().value?.destination?.route
            val currentDestinationParentRoute =
                navController.currentBackStackEntryAsState().value?.destination?.parent?.route
            val visible =
                featureNavigationApis.any { it.startDestination == currentDestinationRoute } || currentDestinationParentRoute == null

            Scaffold(
                bottomBar = {
                    AnimatedVisibility(visible) {
                        BottomBar(
                            navController = navController,
                            currentDestinationParentRoute = currentDestinationParentRoute,
                            items = bottomBarItems
                        )
                    }
                },
                containerColor = Color(0xFFF2F4F7)
            ) { paddingValues ->
                AppNavGraph(
                    navController = navController,
                    featureNavigationApis = featureNavigationApis,
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }

        is AuthState.Unauthenticated, is AuthState.Error -> {
            AuthNavGraph(
                navController = navController,
                authStateViewModel = authStateViewModel
            )
        }
    }
}

@Composable
fun AuthNavGraph(
    navController: NavHostController,
    authStateViewModel: AuthStateViewModel,
) {
    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(
                authStateViewModel = authStateViewModel,
                navController = navController
            )
        }
        composable("register") {
            RegisterScreen(
                authStateViewModel = authStateViewModel,
                navController = navController
            )
        }
    }
}


@Composable
fun RegisterScreen(
    authStateViewModel: AuthStateViewModel,
    navController: NavHostController
) {
    val authState by authStateViewModel.authState.collectAsState()
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F4F7)) // Унифицирован с LoginScreen
            .padding(horizontal = 16.dp)
            .pointerInput(Unit) {
                detectTapGestures { focusManager.clearFocus() }
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Заголовок
        Text(
            text = "Регистрация",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Карточка
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp), // Унифицированный вертикальный отступ
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.elevatedCardElevation(0.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF))
        ) {

            val isError = authState is AuthState.Error
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                OutlinedTextField(
                    value = login,
                    onValueChange = { login = it },
                    label = { Text("Введите логин") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    singleLine = true,
                    shape = RoundedCornerShape(15.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = focusedBorderColor,
                        unfocusedBorderColor = if (isError) errorBorderColor else unfocusedBorderColor,
                        cursorColor = focusedBorderColor,
                        focusedLabelColor = focusedBorderColor,
                        unfocusedLabelColor = unfocusedBorderColor,
                        focusedTextColor = textColor,
                        unfocusedTextColor = textColor,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                    ),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    )
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Введите пароль") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    singleLine = true,
                    shape = RoundedCornerShape(15.dp),
                    visualTransformation = PasswordVisualTransformation(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = focusedBorderColor,
                        unfocusedBorderColor = if (isError) errorBorderColor else unfocusedBorderColor,
                        cursorColor = focusedBorderColor,
                        focusedLabelColor = focusedBorderColor,
                        unfocusedLabelColor = unfocusedBorderColor,
                        focusedTextColor = textColor,
                        unfocusedTextColor = textColor,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                    ),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = { focusManager.clearFocus() }
                    )
                )
            }
        }

        // Кнопка
        Button(
            onClick = { authStateViewModel.register(login, password) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp), // Унифицированный вертикальный отступ
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7A5AF8))
        ) {
            Text("Зарегистрироваться", color = Color.White)
        }

        // Ссылка
        Row(
            modifier = Modifier.padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Есть аккаунт? ", color = Color.Gray)
            TextButton(onClick = { navController.navigate("login") }) {
                Text("Войти", color = Color(0xFF7A5AF8))
            }
        }

        // Состояния
        when (authState) {
            is AuthState.Loading -> CircularProgressIndicator(
                modifier = Modifier.padding(top = 16.dp),
                color = Color(0xFF7A5AF8)
            )

            is AuthState.Error -> Text(
                "Ошибка: ${(authState as AuthState.Error).message}",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 16.dp)
            )

            else -> {}
        }
    }
}

@Composable
fun LoginScreen(
    authStateViewModel: AuthStateViewModel,
    navController: NavHostController
) {
    val authState by authStateViewModel.authState.collectAsState()
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F4F7))
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Заголовок
        Text(
            text = "Вход",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Карточка
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp), // Унифицированный вертикальный отступ
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.elevatedCardElevation(0.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF))
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                val isError = authState is AuthState.Error
                OutlinedTextField(
                    value = login,
                    onValueChange = { login = it },
                    label = { Text("Введите логин") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    singleLine = true,
                    shape = RoundedCornerShape(15.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = focusedBorderColor,
                        unfocusedBorderColor = if (isError) errorBorderColor else unfocusedBorderColor,
                        cursorColor = focusedBorderColor,
                        focusedLabelColor = focusedBorderColor,
                        unfocusedLabelColor = unfocusedBorderColor,
                        focusedTextColor = textColor,
                        unfocusedTextColor = textColor,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                    ),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    )
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Введите пароль") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    singleLine = true,
                    shape = RoundedCornerShape(15.dp),
                    visualTransformation = PasswordVisualTransformation(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = focusedBorderColor,
                        unfocusedBorderColor = if (isError) errorBorderColor else unfocusedBorderColor,
                        cursorColor = focusedBorderColor,
                        focusedLabelColor = focusedBorderColor,
                        unfocusedLabelColor = unfocusedBorderColor,
                        focusedTextColor = textColor,
                        unfocusedTextColor = textColor,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                    ),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = { focusManager.clearFocus() }
                    )
                )
            }
        }

        Button(
            onClick = { authStateViewModel.login(login, password) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7A5AF8))
        ) {
            Text("Войти", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Ссылка
        Row(
            modifier = Modifier.padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Нет аккаунта? ", color = Color.Gray)
            TextButton(onClick = { navController.navigate("register") }) {
                Text("Зарегистрироваться", color = Color(0xFF7A5AF8))
            }
        }


        when (authState) {
            is AuthState.Loading -> CircularProgressIndicator(
                modifier = Modifier.padding(top = 16.dp),
                color = Color(0xFF7A5AF8)
            )

            is AuthState.Error -> Text(
                "Ошибка: ${(authState as AuthState.Error).message}",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 16.dp)
            )

            else -> {}
        }
    }
}

@Composable
private fun BottomBar(
    navController: NavController,
    currentDestinationParentRoute: String?,
    items: List<BottomBarItem>,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier,
        containerColor = Color(0xFFFFFFFF),
        contentColor = Color.Gray
    ) {
        items.forEach { bottomBarItem ->
            NavigationBarItem(
                selected = currentDestinationParentRoute == bottomBarItem.navigationRoute,
                onClick = {
                    navController.navigate(bottomBarItem.navigationRoute) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(bottomBarItem.iconId),
                        contentDescription = null,
                        tint = if (currentDestinationParentRoute == bottomBarItem.navigationRoute) Color(
                            0xFF7A5AF8
                        ) else Color.Gray
                    )
                },
                label = {
                    Text(
                        text = stringResource(bottomBarItem.nameId),
                        color = if (currentDestinationParentRoute == bottomBarItem.navigationRoute) Color(
                            0xFF7A5AF8
                        ) else Color.Gray
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF7A5AF8),
                    unselectedIconColor = Color.Gray,
                    selectedTextColor = Color(0xFF7A5AF8),
                    unselectedTextColor = Color.Gray,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}