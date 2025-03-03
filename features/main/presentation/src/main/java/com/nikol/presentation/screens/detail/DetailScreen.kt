package com.nikol.presentation.screens.detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.nikol.domain.model.Movie
import com.nikol.domain.results.RemoteObtainingMovie
import com.nikol.presentation.R


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DetailScreen(
    navController: NavController,
    movie: Movie,
    viewModel: DetailScreenViewModel = hiltViewModel()
) {
    val state by viewModel.filmState.collectAsState()
    val scrollState = rememberScrollState()
    var expanded by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.getFilm(movie)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when (val currentState = state) {
            is RemoteObtainingMovie.Success -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                ) {
                    // Хедер с изображением
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                    ) {
                        AsyncImage(
                            model = currentState.movie.imageUrl,
                            contentDescription = "Постер фильма",
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)),
                            contentScale = ContentScale.Crop,
                        )

                        // Кнопка назад с эффектом
//                        IconButton(
//                            onClick = { navController.popBackStack() },
//                            modifier = Modifier
//                                .offset(16.dp, 16.dp)
//                                .background(Color.White.copy(0.9f), CircleShape)
//                                .size(48.dp)
//                        ) {
//                            Icon(
//                                painter = painterResource(R.drawable.arrow_back),
//                                contentDescription = "Назад",
//                                tint = Color(0xFF7A5AF8),
//                                modifier = Modifier.size(24.dp)
//                            )
//                        }
                        IconButton(
                            onClick = { navController.popBackStack() },
                            modifier = Modifier
                                .offset(16.dp, 16.dp)
                                .size(48.dp)
                                .background(
                                    color = Color.White,
                                    shape = CircleShape
                                )

                        ) {
                            Icon(
                                painter = painterResource(R.drawable.arrow_back),
                                contentDescription = "Назад",
                                tint = Color(0xFF7A5AF8),
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }

                    // Основной контент
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 24.dp)
                            .fillMaxWidth()
                    ) {
                        // Заголовок и рейтинг
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 24.dp, bottom = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = currentState.movie.title,
                                style = MaterialTheme.typography.headlineLarge.copy(
                                    color = Color(0xFF2D2D2D),
                                    fontWeight = FontWeight.ExtraBold
                                ),
                                modifier = Modifier.weight(1f)
                            )

                            // Рейтинг с анимацией
                            var ratingVisible by remember { mutableStateOf(false) }
                            LaunchedEffect(Unit) { ratingVisible = true }
                            AnimatedVisibility(
                                visible = ratingVisible,
                                enter = fadeIn() + expandVertically(),
                                exit = fadeOut() + shrinkVertically()
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .background(
                                            color = Color(0xFF7A5AF8).copy(alpha = 0.1f),
                                            shape = RoundedCornerShape(8.dp)
                                        )
                                        .padding(horizontal = 10.dp, vertical = 6.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Rounded.Star,
                                        contentDescription = null,
                                        tint = Color(0xFF7A5AF8),
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Text(
                                        text = "%.1f".format(currentState.movie.rating ?: 0.0),
                                        style = MaterialTheme.typography.titleMedium.copy(
                                            color = Color(0xFF7A5AF8),
                                            fontWeight = FontWeight.Bold
                                        ),
                                        modifier = Modifier.padding(start = 4.dp)
                                    )
                                }
                            }
                        }

                        // Жанры с горизонтальным скроллом
                        val genres = currentState.movie.genres
                        if (genres.orEmpty().isNotEmpty()) {
                            Text(
                                text = "Жанры",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    color = Color(0xFF444444)
                                ),
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            LazyRow(
                                modifier = Modifier.padding(bottom = 16.dp),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                items(genres.orEmpty()) { genre ->
                                    ChipView(text = genre)
                                }
                            }
                        }

                        val descriptionText =
                            currentState.movie.description.ifEmpty { "Нет описания" }

                        Column(
                            modifier = Modifier
                                .animateContentSize() // Анимация изменения размера
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                        ) {
                            Text(
                                text = descriptionText,
                                fontSize = 16.sp,
                                color = Color(0xFF666666),
                                maxLines = if (expanded) Int.MAX_VALUE else 3,
                                overflow = TextOverflow.Ellipsis,
                                lineHeight = 24.sp
                            )

                            // Анимированная кнопка
                            val rotationState by animateFloatAsState(
                                targetValue = if (expanded) 180f else 0f,
                                animationSpec = tween(300)
                            )

                            Row(
                                modifier = Modifier
                                    .clickable { expanded = !expanded }
                                    .padding(top = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = if (expanded) "Свернуть" else "Подробнее",
                                    color = Color(0xFF7A5AF8),
                                    fontSize = 14.sp,
                                    modifier = Modifier.padding(end = 8.dp)
                                )

                                Icon(
                                    imageVector = Icons.Default.ArrowDropDown,
                                    contentDescription = if (expanded) "Свернуть" else "Подробнее",
                                    tint = Color(0xFF7A5AF8),
                                    modifier = Modifier
                                        .rotate(rotationState)
                                        .size(20.dp)
                                )
                            }
                        }


                        // Кнопка с эффектом нажатия
                        Spacer(modifier = Modifier.height(24.dp))
                        Button(
                            onClick = {
                                if (currentState.movie.isWatchlist) {
                                    viewModel.deleteMove(currentState.movie.id ?: 0)
                                } else {
                                    viewModel.addMovie(currentState.movie.id ?: 0)
                                }
                                navController.popBackStack()
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF7A5AF8),
                                contentColor = Color.White
                            )
                        ) {
                            Text(
                                text = if (currentState.movie.isWatchlist) "Удалить из библиотеки" else "Добавить в библиотеку",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }
            }

            is RemoteObtainingMovie.Loading -> {
                CircularProgressIndicator()
            }

            is RemoteObtainingMovie.Error -> {
                ErrorMessage(message = currentState.message)
            }

            else -> Unit
        }
    }
}

@Composable
private fun ChipView(text: String) {
    Box(
        modifier = Modifier
            .background(
                color = Color(0xFF7A5AF8).copy(alpha = 0.1f),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(
                color = Color(0xFF7A5AF8),
                fontWeight = FontWeight.Medium
            )
        )
    }
}

@Composable
private fun ErrorMessage(message: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "Ошибка",
            tint = Color(0xFFCC3333),
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = Color(0xFFCC3333)
            ),
            textAlign = TextAlign.Center
        )
    }
}