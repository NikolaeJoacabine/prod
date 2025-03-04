package com.nikol.presentation.screens.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nikol.presentation.screens.detail.ChipView
import com.nikol.presentation.screens.library.shimmerEffect

@Composable
fun ShimmerSearch() {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column {
            // Замена AsyncImage на Box с shimmerEffect последним
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                    .shimmerEffect() // Применяем shimmerEffect последним
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 5.dp)
                            .height(24.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .shimmerEffect() // Применяем shimmerEffect последним
                    )

                    // Замена рейтинга на Box с shimmerEffect последним
                    Box(
                        modifier = Modifier
                            .height(24.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .width(60.dp)
                            .shimmerEffect() // Применяем shimmerEffect последним
                    )
                }

                // Замена года на Box с shimmerEffect последним
                Box(
                    modifier = Modifier
                        .height(20.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .width(40.dp)
                        .padding(top = 8.dp)
                        .shimmerEffect() // Применяем shimmerEffect последним
                )

                // Замена текста описания на Box с shimmerEffect последним
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .padding(top = 8.dp)
                        .shimmerEffect() // Применяем shimmerEffect последним
                )

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        item {
                            ChipView(text = "       ")
                        }
                    }
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Избранное",
                        tint = Color(0xFF744EDC),
                    )
                }
            }
        }
    }
}