package com.nikol.prod

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
                }
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
                authStateViewModel = authStateViewModel,
                featureNavigationApis = featureNavigationApis
            )
        }
    }
}

@Composable
fun AuthNavGraph(
    navController: NavHostController,
    authStateViewModel: AuthStateViewModel,
    featureNavigationApis: List<FeatureApi>
) {
    NavHost(
        navController = navController,
        startDestination = "auth"
    ) {
        composable("auth") {
            AuthScreen(authStateViewModel, navController, featureNavigationApis)
        }
    }
}

@Composable
fun AuthScreen(
    authStateViewModel: AuthStateViewModel,
    navController: NavHostController,
    featureNavigationApis: List<FeatureApi>,
) {
    val authState by authStateViewModel.authState.collectAsState()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (authState) {
            is AuthState.Loading -> CircularProgressIndicator()
            is AuthState.Error -> Text("Ошибка: ${(authState as AuthState.Error).message}")
            else -> {
                TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") })
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { authStateViewModel.login(email, password) }) {
                    Text("Войти")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { authStateViewModel.register(email, password) }) {
                    Text("Зарегистрироваться")
                }
            }
        }
    }

    LaunchedEffect(authState) {
        if (authState is AuthState.Authenticated) {
            navController.navigate(featureNavigationApis.first().navigationRoute) {
                popUpTo("auth") { inclusive = true }
            }
        }
    }
}

@Composable
private fun BottomBar(
    navController: NavController,
    currentDestinationParentRoute: String?,
    items: List<BottomBarItem>,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier
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
                    )
                },
                label = {
                    Text(text = stringResource(bottomBarItem.nameId))
                }
            )
        }
    }
}
