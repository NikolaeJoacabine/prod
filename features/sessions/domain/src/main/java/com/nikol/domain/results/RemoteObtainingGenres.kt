package com.nikol.domain.results

import com.nikol.domain.model.MovieSession

sealed class RemoteObtainingGenres {
    data class Success(val genres: List<MovieSession>) : RemoteObtainingGenres()
    data object Loading : RemoteObtainingGenres()
    data object Neutral : RemoteObtainingGenres()
    data class Error(val message: String) : RemoteObtainingGenres()
}