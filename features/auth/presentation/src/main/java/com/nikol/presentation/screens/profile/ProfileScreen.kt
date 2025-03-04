package com.nikol.presentation.screens.profile

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.nikol.domain.model.Movie
import com.nikol.domain.models.MoviesProfile
import com.nikol.domain.respons.RemoteObtainingUserProfile
import com.nikol.presentation.R
import com.nikol.presentation.nav.LibraryFeatureScreens


@Composable
fun ProfileScreen(navController: NavController, viewModel: ProfileViewModel = hiltViewModel()) {
    val scrollState = rememberScrollState()
    val state by viewModel.profileState.collectAsState()

    LaunchedEffect (Unit){
        viewModel.getProfile()
    }
    when (val currentSate = state) {
        is RemoteObtainingUserProfile.Loading -> {
            CircularProgressIndicator()
        }

        is RemoteObtainingUserProfile.Success -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .background(Color(0xFFF8F7FF))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color(0xFF6A49FF), Color(0xFF8B79FF))
                            )
                        )
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        AnimatedVisibility(
                            visible = true,
                            enter = fadeIn() + slideInVertically { it / 2 },
                            exit = fadeOut()
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(24.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(
                                        text = "Добро пожаловать,",
                                        style = MaterialTheme.typography.titleLarge.copy(
                                            color = Color.White.copy(alpha = 0.8f)
                                        )
                                    )
                                    Text(
                                        text = currentSate.profile.userName,
                                        style = MaterialTheme.typography.headlineLarge.copy(
                                            color = Color.White,
                                            fontWeight = FontWeight.ExtraBold
                                        )
                                    )
                                }

                                IconButton(
                                    onClick = {
                                        viewModel.exit()
                                    },
                                    modifier = Modifier
                                        .background(Color.White.copy(0.2f), CircleShape)
                                        .size(48.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(R.drawable.logout),
                                        contentDescription = "Выход",
                                        tint = Color.White,
                                        modifier = Modifier.size(28.dp)
                                    )
                                }
                            }
                        }
                    }
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (-32).dp)
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(Color.White),
                    elevation = CardDefaults.cardElevation(12.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp)
                    ) {
                        // Статистика
                        AnimatedStatisticsItem(
                            painter = painterResource(R.drawable.movie),
                            title = "Сохранено фильмов",
                            value = "${currentSate.profile.watchlistCount}"
                        )

                        AnimatedStatisticsItem(
                            painter = painterResource(R.drawable.visibility),
                            title = "Посмотрено фильмов",
                            value = "${currentSate.profile.watchedCount}"
                        )

                        Spacer(modifier = Modifier.height(32.dp))

                        // История просмотра
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            items(currentSate.profile.watchedFilms) {
                                MovieCard(navController, film = it) // Пример изображения
                            }
                        }

                        Spacer(modifier = Modifier.height(32.dp))
                    }
                }
            }
        }

        is RemoteObtainingUserProfile.Error -> {}
    }

}

// Карточка фильма с изображением
@Composable
private fun MovieCard(
    navController: NavController,
    modifier: Modifier = Modifier,
    film: MoviesProfile
) {
    val movie = Movie(
        id = film.id,
        title = film.title,
        description = film.description,
        year = film.year,
        imageUrl = film.imageUrl,
        rating = film.rating,
        filmUrl = film.filmUrl,
        genres = film.genres,
        isWatchlist = film.isWatchlist
    )
    Card(
        modifier = modifier
            .width(280.dp)
            .height(180.dp)
            .clickable{navController.navigate(LibraryFeatureScreens.DetailScreen.withObject(movie))},
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(Color.Transparent), // Прозрачный фон, так как у нас картинка
        elevation = CardDefaults.cardElevation(8.dp) // Оставляем тень
    ) {
        Box {
            // Фоновое изображение
            AsyncImage(
                model = film.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Затемнение нижней части для лучшей читаемости текста
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.9f)),
                            startY = 0f,
                            endY = Float.POSITIVE_INFINITY
                        )
                    )
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = film.title,
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    ChipView(
                        text = film.genres.firstOrNull() ?: "",
                        painter = painterResource(R.drawable.theaters)
                    )
                    RatingBadge(film.rating ?: 0.0)
                }
            }
        }
    }
}

// Категории фильмов (Chip)
@Composable
private fun ChipView(text: String, painter: Painter) {
    Row(
        modifier = Modifier
            .background(
                color = Color.White.copy(0.3f), // Светлее для лучшей видимости
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painter,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = text,
            color = Color.White,
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Composable
private fun AnimatedStatisticsItem(painter: Painter, title: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painter,
            contentDescription = null,
            tint = Color(0xFF6A49FF),
            modifier = Modifier.size(28.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color(0xFF666666)
                )
            )
            Text(
                text = value,
                style = MaterialTheme.typography.headlineMedium.copy(
                    color = Color(0xFF2D2D2D),
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

// Рейтинг фильма
@Composable
private fun RatingBadge(rating: Double) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(
                color = Color(0xFF8B79FF).copy(0.7f), // Фиолетовый фон
                shape = RoundedCornerShape(16.dp)
            )
            .border(
                width = 1.dp,
                color = Color(0xFFD1C4E9), // Бледно-фиолетовая обводка
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(18.dp)
        )
        Text(
            text = "%.1f".format(rating),
            color = Color.White,
            style = MaterialTheme.typography.titleMedium
        )
    }
}