package com.nikol.presentation.screens.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.nikol.domain.model.Movie

@Composable
fun SessionScreen(
    moviesStateViewModel: SessionsViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val moviesState by moviesStateViewModel.moviesState.collectAsState()
    @Composable
    fun AddMovieScreen(
        moviesStateViewModel: SessionsViewModel = hiltViewModel(),
        navController: NavHostController
    ) {
        // State variables
        var guestLogin by remember { mutableStateOf("") }
        var selectedGenres by remember { mutableStateOf("") }
        val currentSeance = "Текущий сеанс"

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
        ) {
            Text(text = currentSeance, style = MaterialTheme.typography.headlineLarge)

            // Guest login and add button
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = guestLogin,
                    onValueChange = { guestLogin = it },
                    label = { Text("login гостя") },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = { /* Handle add guest logic */ }) {
                    Text("Добавить")
                }
            }

            // Genre selection toggle buttons
            Row(modifier = Modifier.padding(vertical = 16.dp)) {
                AssistChip(onClick = {}, label = { Text("Евгений") })
                Spacer(modifier = Modifier.width(8.dp))
                AssistChip(onClick = {}, label = { Text("Не Евгений") })
            }

            // Common genres input
            TextField(
                value = selectedGenres,
                onValueChange = { selectedGenres = it },
                label = { Text("Общие жанры") },
                modifier = Modifier.fillMaxWidth()
            )

            // Recommendations section
            Text(text = "Подборка по общим жанрам", style = MaterialTheme.typography.headlineMedium)
            val movies = listOf<Movie>(
                Movie(
                    url = "",
                    urlImage = "",
                    description = "Описание 1",
                    title = "Название 1"
                ),
                Movie(
                    url = "",
                    urlImage = "",
                    description = "Описание 2",
                    title = "Название 2"
                ),
                Movie(
                    url = "",
                    urlImage = "",
                    description = "Описание 3",
                    title = "Название  3"
                ),
            )
            LazyColumn {
                items(movies) { movie ->
                    MovieCard(movie)
                }
            }
        }
    }

}

@Composable
fun MovieCard(movie: Movie) {
    Card(modifier = Modifier.padding(vertical = 8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = movie.title, style = MaterialTheme.typography.headlineMedium)
//                Text(text = movie.genre, style = MaterialTheme.typography.body1)
//                Text(text = "${movie.rating}★", style = MaterialTheme.typography.body2)
        }
    }
}
