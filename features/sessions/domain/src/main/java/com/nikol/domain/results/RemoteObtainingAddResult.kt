package com.nikol.domain.results

import com.nikol.domain.model.MovieSession

sealed class RemoteObtainingAddResult {
    data object Success : RemoteObtainingAddResult()
    data object Loading : RemoteObtainingAddResult()
    data object Neutral : RemoteObtainingAddResult()
    data class Error(val message: String) : RemoteObtainingAddResult()
}