package com.nikol.domain.results

import com.nikol.domain.model.User

sealed class RemoteObtainingLoginResult {
    data class Success(val currentToken: String) : RemoteObtainingLoginResult()
    data object Loading : RemoteObtainingLoginResult()
    data class LoginError(val message: String) : RemoteObtainingLoginResult()
    data class NetworkError(val message: String) : RemoteObtainingLoginResult()
}