package com.nikol.data.utils

import com.nikol.data.remote.models.SessionMovieDTO
import com.nikol.domain.model.MovieSession

fun SessionMovieDTO.toDDomain() =
    MovieSession(
        id = this.id ?: 0,
        title = this.title ?: "",
        year = this.year ?: 0,
        description = this.description ?: "",
        imageUrl = this.imageUrl ?: "",
        rating = this.rating ?: 0.0,
        genres = this.genres,
        filmUrl = this.filmUrl
    )
