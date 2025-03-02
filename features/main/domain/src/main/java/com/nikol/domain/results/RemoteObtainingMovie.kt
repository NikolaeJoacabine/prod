package com.nikol.domain.results

import com.nikol.domain.model.Movie

sealed class RemoteObtainingMovie {
    data class Success(val movie: Movie) : RemoteObtainingMovie()
    data object Loading : RemoteObtainingMovie()
    data class Error(val message: String) : RemoteObtainingMovie()
}