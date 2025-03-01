package com.nikol.domain.results

sealed class RemoteObtainingLibraryActionResult {
    data object Success : RemoteObtainingLibraryActionResult()
    data object Loading : RemoteObtainingLibraryActionResult()
    data object Neutral: RemoteObtainingLibraryActionResult()
    data class NetWorkError(val message: String) : RemoteObtainingLibraryActionResult()
    data class Error(val message: String) : RemoteObtainingLibraryActionResult()
}