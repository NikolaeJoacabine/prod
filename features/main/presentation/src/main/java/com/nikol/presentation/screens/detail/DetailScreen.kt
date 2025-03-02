package com.nikol.presentation.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nikol.domain.model.Movie


@Composable
fun DetailScreen(navController: NavController, movie: Movie) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Заголовок
        Text(
            text = "Название",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333)
        )

        // Секция "О фильме"
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "О фильме",
                fontSize = 18.sp,
                color = Color(0xFF666666)
            )

            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Рейтинг",
                tint = Color(0xFFFFD700),
                modifier = Modifier.size(20.dp)
            )

            Text(
                text = "5",
                fontSize = 16.sp,
                color = Color(0xFF333333)
            )
        }

        // Описание
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Описание",
                tint = Color(0xFF7A5AF8),
                modifier = Modifier.size(20.dp)
            )

            Text(
                text = "Описание фильма",
                fontSize = 16.sp,
                color = Color(0xFF333333)
            )
        }

        // Жанры
        Column {
            Text(
                text = "Жанры:",
                fontSize = 16.sp,
                color = Color(0xFF666666),
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                text = "страшилка-пугалка-кошмарка",
                fontSize = 14.sp,
                color = Color(0xFF333333)
            )
        }

        // Год выхода
        Text(
            text = "Год выхода: 2007",
            fontSize = 16.sp,
            color = Color(0xFF666666)
        )

        // Актеры/Режиссер
        Text(
            text = "Актеры/Режиссер: Райан Гослинг",
            fontSize = 16.sp,
            color = Color(0xFF666666)
        )

        // Темы для обсуждения
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Темы",
                tint = Color(0xFF7A5AF8),
                modifier = Modifier.size(20.dp)
            )

            Text(
                text = "Темы для обсуждения",
                fontSize = 16.sp,
                color = Color(0xFF333333)
            )
        }

        // Кнопка
        Button(
            onClick = { /* TODO */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF7A5AF8)
            )
        ) {
            Text(
                text = "Добавить в библиотеку",
                fontSize = 16.sp,
                color = Color.White,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}