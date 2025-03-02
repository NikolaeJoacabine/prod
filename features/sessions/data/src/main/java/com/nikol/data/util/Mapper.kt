package com.nikol.data.util

import com.nikol.data.remote.models.SessionMovieDTO
import com.nikol.domain.model.MovieSession

fun SessionMovieDTO.toDomain() =
    MovieSession(
        id = this.id ?: 0,
        title = this.title ?: "",
        year = this.year ?: 0,
        description = this.description ?: "",
        imageUrl = this.imageUrl ?: "",
        rating = this.rating ?: 0.0
    )