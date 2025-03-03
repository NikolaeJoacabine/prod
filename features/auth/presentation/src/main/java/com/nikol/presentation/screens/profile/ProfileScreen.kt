package com.nikol.presentation.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nikol.presentation.R

@Composable
fun ProfileScreen(viewModel: ProfileViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Заголовок
        Spacer(Modifier.height(50.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Привет, Евгений!",
                style = MaterialTheme.typography.headlineMedium // Обновлено
            )
            IconButton(onClick = { /* TODO: Add logout action */ }) {
                Icon(
                    imageVector = Icons.Default.ExitToApp, // Заменить на иконку выхода
                    contentDescription = "Выход",
                    tint = Color(0xFF6A49FF) // Цвет иконки
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = "",
            onValueChange = {  },
            label = { Text("Изменить логин") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            singleLine = true,
            shape = RoundedCornerShape(15.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Статистика
        Text(text = "Сохранено фильмов: 10", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Посмотрено фильмов: 0", style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(16.dp))

        // История просмотра
        Text(
            text = "История просмотра",
            style = MaterialTheme.typography.headlineSmall, // Обновлено
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(3) { // Пример с 3 карточками
                MovieCard()
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Достижения
        Text(
            text = "Достижения",
            style = MaterialTheme.typography.headlineSmall, // Обновлено
            modifier = Modifier.padding(bottom = 8.dp)
        )

        AchievementItem(
            text = "Сохранить 10 фильмов",
            isCompleted = true
        )
        AchievementItem(
            text = "Посмотреть 100 фильмов",
            isCompleted = false
        )
    }
}

@Composable
fun MovieCard() {
    Card(
        modifier = Modifier
            .width(350.dp)
            .height(220.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.account_circle), // Замените на ваше изображение
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Spacer(Modifier.height(18.dp))
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 14.dp),
                        text = "Название",
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(18.dp))
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 14.dp),
                        text = "Жанры",
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 18.sp,
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "5",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    Icon(
                        modifier = Modifier
                            .size(30.dp),
                        imageVector = Icons.Outlined.Star,
                        contentDescription = "Рейтинг",
                        tint = Color(0xFFFFD700) // Золотой цвет для звездочки
                    )
                }
            }
        }
    }
}
@Composable
fun AchievementItem(text: String, isCompleted: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = text, modifier = Modifier.weight(1f))
        Icon(
            imageVector = if (isCompleted) Icons.Default.Check else Icons.Outlined.Check,
            contentDescription = null,
            tint = if (isCompleted) Color(0xFF6A49FF) else Color.Gray
        )
    }
}