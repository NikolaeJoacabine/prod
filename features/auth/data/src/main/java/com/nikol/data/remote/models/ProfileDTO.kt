package com.nikol.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ProfileDTO(
    @SerialName("username") val userName: String,
    @SerialName("watched_count")val watchedCount: Int,
    @SerialName("watchlist_count")val watchlistCount: Int,
    @SerialName("watched_films")val watchedFilms: List<MoviesProfileDTO>
)
