package com.nikol.presentation.screens.detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
import com.nikol.domain.results.RemoteObtainingMovie
import com.nikol.domain.results.RemoteObtainingTopics
import com.nikol.presentation.R


@Composable
fun DetailScreen(
    navController: NavController,
    movie: Movie,
    viewModel: DetailScreenViewModel = hiltViewModel()
) {
    val state by viewModel.filmState.collectAsState()
    val stateTopics by viewModel.topicsState.collectAsState()
    var expanded by remember { mutableStateOf(false) }
    var showDiscussion by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.getFilm(movie)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when (val currentState = state) {
            is RemoteObtainingMovie.Success -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    // Заголовок с изображением и кнопкой "Назад"
                    item {
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
                                    .clip(
                                        RoundedCornerShape(
                                            bottomStart = 32.dp,
                                            bottomEnd = 32.dp
                                        )
                                    ),
                                contentScale = ContentScale.Crop
                            )
                            IconButton(
                                onClick = { navController.popBackStack() },
                                modifier = Modifier
                                    .offset(16.dp, 16.dp)
                                    .size(48.dp)
                                    .background(color = Color.White, shape = CircleShape)
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.arrow_back),
                                    contentDescription = "Назад",
                                    tint = Color(0xFF7A5AF8),
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }
                    }

                    // Информация о фильме (название, рейтинг, жанры, описание)
                    item {
                        Column(
                            modifier = Modifier
                                .padding(horizontal = 24.dp)
                                .fillMaxWidth()
                        ) {
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
                                    .animateContentSize()
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
                        }
                    }

                    // Кнопка "Обсудить с AI"
                    item {
                        Column(
                            modifier = Modifier
                                .padding(horizontal = 24.dp)
                                .fillMaxWidth()
                        ) {
                            OutlinedButton(
                                onClick = {
                                    showDiscussion = !showDiscussion // Переключаем состояние
                                    if (showDiscussion) {
                                        val movie = (state as? RemoteObtainingMovie.Success)?.movie
                                        movie?.let { viewModel.getTopics(it.title, it.year ?: 0) }
                                    }
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(56.dp),
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    containerColor = if (showDiscussion) Color(0xFF7A5AF8).copy(alpha = 0.1f)
                                    else Color.Transparent,
                                    contentColor = Color(0xFF7A5AF8)
                                ),
                                border = BorderStroke(1.dp, Color(0xFF7A5AF8).copy(alpha = 0.3f))
                            ) {
                                Icon(
                                    painter = painterResource(
                                        if (showDiscussion) R.drawable.close
                                        else R.drawable.chat_bubble
                                    ),
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    text = if (showDiscussion) "Скрыть обсуждение"
                                    else "Обсудить с AI",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.SemiBold)
                                )
                            }
                        }
                    }

                    if (showDiscussion) {
                        when (val topics = stateTopics) {
                            is RemoteObtainingTopics.Success -> {
                                items(
                                    items = topics.topics,
                                ) { item ->
                                    DiscussionTopicItem(
                                        text = item,
                                    )
                                }
                            }

                            is RemoteObtainingTopics.Loading -> {
                                item { LoadingDotsWaveEffect() }
                            }

                            is RemoteObtainingTopics.Error -> {
                                item { ErrorMessage(message = topics.message) }
                            }

                            else -> Unit
                        }
                    }

                    item {
                        Column(
                            modifier = Modifier
                                .padding(horizontal = 24.dp)
                                .fillMaxWidth()
                        ) {
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
                                    .padding(top = 24.dp),
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
            }

            is RemoteObtainingMovie.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            is RemoteObtainingMovie.Error -> {
                ErrorMessage(message = currentState.message)
            }

            else -> Unit
        }
    }
}

@Composable
fun LoadingDotsWaveEffect() {
    val infiniteTransition = rememberInfiniteTransition()
    val offsetY = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 20f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = CubicBezierEasing(0.4f, 0.0f, 0.2f, 1.0f)),
            repeatMode = RepeatMode.Reverse
        )
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp)
            .graphicsLayer { translationY = offsetY.value },
        horizontalArrangement = Arrangement.Center
    ) {
        LoadingDotsAnimation()
    }
}

@Composable
fun DiscussionTopicItem(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp)
            .background(
                color = Color(0xFF7A5AF8).copy(alpha = 0.08f),
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = Color(0xFF7A5AF8).copy(alpha = 0.1f),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "🤖",
                    fontSize = 18.sp
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = Color(0xFF444444),
                    lineHeight = 24.sp,
                    letterSpacing = 0.1.sp
                ),
                modifier = Modifier.weight(1f)
            )
        }
    }
}


@Composable
fun LoadingDotsAnimation() {
    val dotSize = 12.dp
    val animationDuration = 600
    val infiniteTransition = rememberInfiniteTransition()

    val dot1Scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(animationDuration, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val dot2Scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(animationDuration, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse,
            initialStartOffset = StartOffset(200)
        )
    )

    val dot3Scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(animationDuration, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse,
            initialStartOffset = StartOffset(400)
        )
    )

    Row(
        modifier = Modifier.padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Dot(scale = dot1Scale, size = dotSize)
        Dot(scale = dot2Scale, size = dotSize)
        Dot(scale = dot3Scale, size = dotSize)
    }
}

@Composable
fun Dot(scale: Float, size: Dp) {
    Box(
        modifier = Modifier
            .size(size)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .background(
                color = Color(0xFF7A5AF8),
                shape = CircleShape
            )
    )
}

@Composable
fun ChipView(text: String) {
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