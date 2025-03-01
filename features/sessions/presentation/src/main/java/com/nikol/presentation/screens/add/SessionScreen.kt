package com.nikol.presentation.screens.add

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.nikol.domain.model.Movie

@Composable
fun SessionScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)), // Серый фон
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(200.dp))
        Text(
            text = "Поиск фильма в паре",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Логин второго человека") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        BubbleLayout()
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = { },
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
fun BubbleLayout() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp) // Задаем высоту для размещения пузырьков
    ) {
        Bubble(text = "драма", size = 80.dp, offsetX = 40.dp, offsetY = 40.dp)
        Bubble(text = "комедия", size = 90.dp, offsetX = 140.dp, offsetY = 0.dp)
        Bubble(text = "триллер", size = 90.dp, offsetX = 180.dp, offsetY = 110.dp)
        Bubble(text = "боевик", size = 70.dp, offsetX = 260.dp, offsetY = 40.dp)
        Bubble(text = "фантастика", size = 110.dp, offsetX = (40).dp, offsetY = 150.dp)
        Bubble(text = "детектив", size = 80.dp, offsetX = 300.dp, offsetY = 160.dp)
        Bubble(text = "ужас", size = 50.dp, offsetX = 50.dp, offsetY = 270.dp)
        Bubble(text = "романтика", size = 100.dp, offsetX = 150.dp, offsetY = 220.dp)
    }
}

@Composable
fun Bubble(text: String, size: Dp, offsetX: Dp, offsetY: Dp, onClick: () -> Unit = {}) {
    // Цвета для обычного и нажатого состояния
    val defaultColor = Color(0x99AC8EFF)
    val pressedColor = Color(0xFF744EDC)

    // Состояние для отслеживания нажатия
    var isPressed by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .size(size) // Устанавливаем размер пузырька
            .offset(x = offsetX, y = offsetY) // Расположение пузырька
            .clip(CircleShape) // Обрезаем для круглой формы
            .background(if (isPressed) pressedColor else defaultColor, CircleShape) // Меняем цвет в зависимости от состояния
            .border(2.dp, Color(0xFF744EDC), CircleShape) // Граница пузырька
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