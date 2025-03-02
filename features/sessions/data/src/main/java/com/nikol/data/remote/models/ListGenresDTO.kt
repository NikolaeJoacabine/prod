package com.nikol.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class ListGenresDTO (
    val genres: List<String>
)