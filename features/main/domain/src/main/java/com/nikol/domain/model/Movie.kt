package com.nikol.domain.model

data class Movie(
    val id: Int? = null,
    val title: String,
    val year: Int? = null,
    val description: String,
    val imageUrl: String? = null,
    val rating: Double? = null,
    val filmUrl: String? = null,
    val geners: List<String> = emptyList(),
    val isWatchlist: Boolean = false
)
