package com.nikol.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen(userName: String, savedMoviesCount: Int, watchedMoviesCount: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        GreetingSection(userName)
        Spacer(modifier = Modifier.height(16.dp))
        MoviesStats(savedMoviesCount, watchedMoviesCount)
        Spacer(modifier = Modifier.height(16.dp))
        ViewingHistory()
        Spacer(modifier = Modifier.height(16.dp))
        AchievementsSection()
    }
}

@Composable
fun GreetingSection(userName: String) {
    Text(
        text = "Привет, $userName!",
        style = MaterialTheme.typography.headlineMedium
    )
    TextField(
        value = userName,
        onValueChange = {},
        label = { Text("Изменить имя") },
        trailingIcon = {
            Icon(Icons.Default.Check, contentDescription = "Сохранить имя")
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun MoviesStats(saved: Int, watched: Int) {
    Column {
        Text(text = "Сохранено фильмов: $saved")
        Text(text = "Посмотрено фильмов: $watched")
    }
}

@Composable
fun ViewingHistory() {
    Text(text = "История просмотра", style = MaterialTheme.typography.headlineMedium)

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "название", style = MaterialTheme.typography.headlineMedium)
            Text(text = "теги, жанры")
            RatingBar(rating = 5) // Просто пример, для отображения рейтинга
        }
    }
}

@Composable
fun AchievementsSection() {
    Text(text = "Достижения", style = MaterialTheme.typography.headlineMedium)

    Column {
        AchievementItem(title = "Сохранить 10 фильмов", achieved = true)
        AchievementItem(title = "Посмотреть 100 фильмов", achieved = true)
    }
}

@Composable
fun AchievementItem(title: String, achieved: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, modifier = Modifier.weight(1f))
        if (achieved) {
            Icon(Icons.Default.Check, contentDescription = "Достигнуто")
        }
    }
}

// Пример простой рейтинговой компоненты
@Composable
fun RatingBar(rating: Int) {
    Text(text = "$rating★")
}