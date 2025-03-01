package com.nikol.domain.results

import com.nikol.domain.model.SessionMovie

sealed class RemoteObtainingLibrary {
    data class Success(val library: List<SessionMovie>) : RemoteObtainingLibrary()
    data object Loading : RemoteObtainingLibrary()
    data class Error(val message: String) : RemoteObtainingLibrary()
}