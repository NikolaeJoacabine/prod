package com.nikol.data.utils

import com.nikol.data.remote.models.MovieDTO
import com.nikol.domain.model.Movie

fun MovieDTO.toDomain() =
    Movie(
        id = this.id ?: 0,
        title = this.title ?: "",
        year = this.year ?: 0,
        description = this.description ?: "",
        imageUrl = this.imageUrl ?: "",
        rating = this.rating ?: 0.0
    )