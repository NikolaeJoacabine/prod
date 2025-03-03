package com.nikol.domain.models

data class UserProfile(
    val userName: String,
    val watchedCount: Int,
    val watchlistCount: Int,
    val watchedFilms: List<MoviesProfile>
)