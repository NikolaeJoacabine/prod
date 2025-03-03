package com.nikol.presentation.screens.add

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.nikol.domain.model.Movie
import com.nikol.domain.results.RemoteObtainingSession
import com.nikol.presentation.nav.LibraryFeatureScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SessionScreen(
    navController: NavController,
    viewModel: SessionsViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    var login by rememberSaveable { mutableStateOf("") }
    var genres by rememberSaveable { mutableStateOf<List<String>>(listOf()) } // Управляемое состояние списка жанров
    var showBottomSheet by rememberSaveable { mutableStateOf(false) } // Управляем состояние показа листа
    val sessionState by viewModel.sessionState.collectAsState()
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
        confirmValueChange = { it != SheetValue.PartiallyExpanded }
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .scrollable(
                state = rememberScrollableState { delta ->
                    delta
                },
                orientation = Orientation.Vertical
            )
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Управляем показом BottomSheet
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false }, // Закрытие листа
                sheetState = sheetState,
                containerColor = Color(0xFFF2F4F7),
                tonalElevation = 8.dp
            ) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    when (val currentState = sessionState) {
                        is RemoteObtainingSession.Success -> {
                            val movies = currentState.movies.map {
                                Movie(
                                    id = it.id,
                                    title = it.title,
                                    year = it.year,
                                    description = it.description,
                                    imageUrl = it.imageUrl,
                                    rating = it.rating,
                                    filmUrl = it.filmUrl,
                                    genres = it.genres,
                                )
                            }
                            items(movies) { movie ->
                                movie.id?.let {
                                    MovieItem(
                                        navController,
                                        movie,
                                        onClick = { viewModel.addMovie(it) })
                                }
                            }
                        }

                        is RemoteObtainingSession.Error -> {
                            item {
                                Text(
                                    text = "Не удалось найти пользователя",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }

                        else -> {
                            item {
                                Text(
                                    text = "Нет данных для отображения",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "Поиск фильма в паре",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 12.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextField(
                value = login,
                onValueChange = { login = it },
                modifier = Modifier
                    .weight(1f)
                    .background(Color(0xFFF2F4F7), RoundedCornerShape(24.dp)),
                placeholder = {
                    Text(
                        text = "Логин гостя",
                        color = Color(0xFF7A5AF8).copy(alpha = 0.7f),
                        fontSize = 16.sp
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = Color(0xFF333333),
                    unfocusedTextColor = Color(0xFF333333),
                    cursorColor = Color(0xFF7A5AF8),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                trailingIcon = {
                    if (login.isNotEmpty()) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Очистить",
                            tint = Color(0xFF7A5AF8),
                            modifier = Modifier
                                .clickable { login = "" }
                                .size(24.dp)
                        )
                    }
                },
                shape = RoundedCornerShape(24.dp),
                singleLine = true,
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 16.sp,
                    lineHeight = 20.sp
                ),
            )
        }
        Spacer(modifier = Modifier.height(24.dp))

        BubbleLayout(genres = genres, onGenreClick = { genre ->
            genres = if (genres.contains(genre)) {
                genres - genre
            } else {
                genres + genre
            }
        })

        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {
                viewModel.addUser(login, genres)
                showBottomSheet = true // Открываем лист при нажатии
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF744EDC)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Посмотреть варианты", color = Color.White)
        }
    }
}

@Composable
fun BubbleLayout(genres: List<String>, onGenreClick: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp) // Задаем высоту для размещения пузырьков
    ) {
        // Передаем onGenreClick в каждый Bubble
        Bubble(
            text = "драма",
            size = 80.dp,
            offsetX = 20.dp,
            offsetY = 40.dp,
            onClick = { onGenreClick("драма") })
        Bubble(
            text = "комедия",
            size = 90.dp,
            offsetX = 120.dp,
            offsetY = 0.dp,
            onClick = { onGenreClick("комедия") })
        Bubble(
            text = "триллер",
            size = 90.dp,
            offsetX = 160.dp,
            offsetY = 110.dp,
            onClick = { onGenreClick("триллер") })
        Bubble(
            text = "боевик",
            size = 70.dp,
            offsetX = 240.dp,
            offsetY = 40.dp,
            onClick = { onGenreClick("боевик") })
        Bubble(
            text = "фантастика",
            size = 110.dp,
            offsetX = 20.dp,
            offsetY = 150.dp,
            onClick = { onGenreClick("фантастика") })
        Bubble(
            text = "детектив",
            size = 90.dp,
            offsetX = 260.dp,
            offsetY = 160.dp,
            onClick = { onGenreClick("детектив") })
        Bubble(
            text = "ужас",
            size = 50.dp,
            offsetX = 30.dp,
            offsetY = 270.dp,
            onClick = { onGenreClick("ужас") })
        Bubble(
            text = "романтика",
            size = 100.dp,
            offsetX = 130.dp,
            offsetY = 220.dp,
            onClick = { onGenreClick("романтика") })
    }
}

@Composable
fun Bubble(text: String, size: Dp, offsetX: Dp, offsetY: Dp, onClick: () -> Unit) {
    // Цвета для обычного и нажатого состояния
    val defaultColor = Color(0x99AC8EFF)
    val pressedColor = Color(0xFF744EDC)

    // Состояние для отслеживания нажатия
    var isPressed by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .size(size) // Устанавливаем размер пузырька
            .offset(x = offsetX, y = offsetY) // Расположение пузырька
            .clip(CircleShape) // Обрезаем для круглой формы
            .background(
                if (isPressed) pressedColor else defaultColor,
                CircleShape
            ) // Меняем цвет в зависимости от состояния
            .clickable(
                onClick = {
                    isPressed = !isPressed // Переключение состояния
                    onClick.invoke() // Вызов переданной функции
                }
            ),
        contentAlignment = Alignment.Center // Центрируем текст
    ) {
        Text(
            text = text,
            color = Color.White,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MovieItem(navController: NavController, item: Movie, onClick: () -> Unit) {
    var isFavorite by remember { mutableStateOf(false) }
    val rotation = animateFloatAsState(targetValue = if (isFavorite) 360f else 0f, label = "")
    val scale = animateFloatAsState(targetValue = if (isFavorite) 1.2f else 1f, label = "")
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { navController.navigate(LibraryFeatureScreens.DetailScreen.withObject(item)) },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            ) {
                AsyncImage(
                    model = item.imageUrl,
                    contentDescription = "Постер фильма",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
                    contentScale = ContentScale.Crop,
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp) // Уменьшено с 16.dp
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = item.title,
                        color = Color(0xFF7A5AF8),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )

                    item.rating?.let {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .background(
                                    color = Color(0xFFF5F3FF),
                                    shape = RoundedCornerShape(6.dp)
                                )
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Star,
                                contentDescription = null,
                                tint = Color(0xFFFFD700),
                                modifier = Modifier.size(18.dp)
                            )

                            Text(
                                text = "%.1f".format(it),
                                color = Color(0xFF7A5AF8),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(start = 4.dp)
                            )
                        }
                    }
                }

                item.year?.let {
                    Text(
                        text = it.toString(),
                        color = Color(0xFF666666),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(top = 4.dp)
                    ) // Уменьшено с 6.dp
                }
                item.genres?.let {
                    Text(
                        text = it.take(2).joinToString(separator = ", "),
                        color = Color(0xFF333333),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 4.dp)
                    ) // Уменьшено с 8.dp
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "",
                        color = Color(0xFF333333),
                        fontSize = 14.sp,
                        lineHeight = 18.sp,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    IconButton(
                        onClick = {
                            isFavorite = !isFavorite
                            onClick()
                        },
                        modifier = Modifier
                            .size(36.dp)
                    ) {
                        AnimatedContent(
                            targetState = isFavorite,
                            transitionSpec = {
                                fadeIn() with fadeOut()
                            },
                            label = ""
                        ) { favorite ->
                            Icon(
                                imageVector = if (favorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                contentDescription = "Избранное",
                                tint = if (favorite) Color.Red else Color(0xFF744EDC),
                                modifier = Modifier
                                    .graphicsLayer {
                                        rotationZ = rotation.value
                                        scaleX = scale.value
                                        scaleY = scale.value
                                    }
                            )
                        }
                    }
                }
            }
        }
    }
}
