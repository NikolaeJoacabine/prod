package com.nikol.domain.results

import com.nikol.domain.model.Movie

sealed class RemoteObtainingGenres {
    data class Success(val genres: List<Movie>) : RemoteObtainingGenres()
    data object Loading : RemoteObtainingGenres()
    data object Neutral : RemoteObtainingGenres()
    data class Error(val message: String) : RemoteObtainingGenres()
}