package com.nikol.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val year: Int? = null,
    val description: String,
    val imageUrl: String,
    val rating: Double? = null
)
