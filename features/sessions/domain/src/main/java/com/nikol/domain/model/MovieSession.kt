package com.nikol.domain.model

data class MovieSession(
    val id: Int,
    val title: String,
    val year: Int? = null,
    val description: String,
    val imageUrl: String,
    val genres: List<String>? = null,
    val rating: Double? = null,
    val filmUrl: String? = null
)
