package com.nikol.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDTO(
    @SerialName("id") var id: Int? = null,
    @SerialName("title") var title: String? = null,
    @SerialName("year") var year: Int? = null,
    @SerialName("description") var description: String? = null,
    @SerialName("image_url") var imageUrl: String? = null,
    @SerialName("genres") var genres: List<String>? = null,
    @SerialName("rating") var rating: Double? = null,
    @SerialName("is_watchlisted") var isWatchlist: Boolean = false
)