package com.nikol.data.utils

import com.nikol.data.remote.models.MovieDTO
import com.nikol.domain.model.Movie


fun MovieDTO.toDomain() =
    Movie(
        id = this.id ?: 0,
        title = this.title ?: "",
        year = this.year,
        description = this.description ?: "",
        imageUrl = this.imageUrl ?: "",
        rating = this.rating ?: 0.0,
        genres = this.genres,
        isWatchlist = this.isWatchlist
    )

fun Movie.toEntity() =
    MovieDTO(
        id = this.id,
        title = this.title,
        year = this.id,
        description = this.description,
        imageUrl = this.imageUrl,
        rating = this.rating,
        genres = this.genres,
        isWatchlist = this.isWatchlist
    )