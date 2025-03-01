package com.nikol.domain.results

import com.nikol.domain.model.Movie

sealed class RemoteObtainingLibrary {
    data class Success(val library: List<Movie>) : RemoteObtainingLibrary()
    data object Loading : RemoteObtainingLibrary()
    data class Error(val message: String) : RemoteObtainingLibrary()
}