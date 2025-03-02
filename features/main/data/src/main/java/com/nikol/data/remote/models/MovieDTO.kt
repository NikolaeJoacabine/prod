package com.nikol.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class MovieDTO(
    val url: String,
    val urlImage: String,
    val description: String,
    val title: String
)