package com.nikol.domain.results

import com.nikol.domain.model.MovieSession

sealed class RemoteObtainingMovies {
    data class Success(val library: List<MovieSession>) : RemoteObtainingMovies()
    data object Loading : RemoteObtainingMovies()
    data object Neutral : RemoteObtainingMovies()
    data class Error(val message: String) : RemoteObtainingMovies()
}