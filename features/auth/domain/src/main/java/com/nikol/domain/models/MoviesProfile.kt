package com.nikol.domain.models

data class MoviesProfile(
    val id: Int? = null,
    val title: String,
    val year: Int? = null,
    val description: String,
    val imageUrl: String? = null,
    val rating: Double? = null,
    val filmUrl: String? = null,
    val genres: List<String> = emptyList(),
    val isWatchlist: Boolean = false
)