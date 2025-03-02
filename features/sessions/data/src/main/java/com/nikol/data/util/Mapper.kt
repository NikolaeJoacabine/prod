package com.nikol.data.util

import com.nikol.data.remote.models.SessionMovieDTO
import com.nikol.domain.model.Movie

fun SessionMovieDTO.toDomain() =
    Movie(
        url = this.url,
        urlImage = this.urlImage,
        description = this.description,
        title = this.title
    )