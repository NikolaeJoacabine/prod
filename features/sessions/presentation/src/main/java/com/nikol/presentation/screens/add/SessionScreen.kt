package com.nikol.presentation.screens.add

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.nikol.domain.model.Movie

@Composable
fun SessionScreen() {
    // Определение стилей
    val purpleColor = Color(0xFF6A5BCE)
    val textColor = Color(0xFF000000)
    var login = ""

    // Контент экрана
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Заголовок
        Text(
            text = "Поиск фильма в паре",
            color = textColor,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

//        // Поле ввода логина второго человека
//        TextField(
//            value = "",
//            onValueChange = {},
//            placeholder = { Text(text = "Логин второго человека") },
//            modifier = Modifier.fillMaxWidth(),
//            colors = TextField(
//                value = yourText,
//                onValueChange = { yourText = it },
//                colors = TextFieldDefaults.colors() // По умолчанию
//            )
//        )

        OutlinedTextField(
            value = login,
            onValueChange = { login = it },
            label = { Text("Логин второго человека") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            singleLine = true,
            shape = RoundedCornerShape(15.dp)
        )

        // Ряд с жанрами
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            GenreButton(text = "фантастика", color = purpleColor)
            GenreButton(text = "драма", color = purpleColor)
            GenreButton(text = "боевик", color = purpleColor)
        }

        // Кнопка "Добавить"
        Button(
            onClick = { /* Действие по добавлению */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(purpleColor)
        ) {
            Text(text = "Добавить", color = Color.White)
        }
    }
}

@Composable
fun GenreButton(text: String, color: Color) {
    Button(
        onClick = { /* Действие по выбору жанра */ },
        modifier = Modifier.padding(horizontal = 4.dp),
        colors = ButtonDefaults.buttonColors( Color.Transparent, contentColor = color),
        border = BorderStroke(1.dp, color)
    ) {
        Text(text = text)
    }
}