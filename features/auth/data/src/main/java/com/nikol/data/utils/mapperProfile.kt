package com.nikol.data.utils

import com.nikol.data.remote.models.MoviesProfileDTO
import com.nikol.data.remote.models.ProfileDTO
import com.nikol.domain.models.MoviesProfile
import com.nikol.domain.models.UserProfile

fun ProfileDTO.toDomain() =
    UserProfile(
        userName = this.userName,
        watchedCount = this.watchedCount,
        watchlistCount = this.watchlistCount,
        watchedFilms = this.watchedFilms.map { it.toDomain() }
    )

fun MoviesProfileDTO.toDomain() =
    MoviesProfile(
        id = this.id,
        title = this.title ?: "",
        year = this.year,
        description = this.description ?: "",
        imageUrl = this.imageUrl,
        rating = this.rating,
        genres = this.genres,
        isWatchlist = this.isWatchlist
    )