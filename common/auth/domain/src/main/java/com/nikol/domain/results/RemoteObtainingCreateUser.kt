package com.nikol.domain.results

sealed class RemoteObtainingCreateUser {
    data class Success(val currentToken: String) : RemoteObtainingCreateUser()
    data object Loading : RemoteObtainingCreateUser()
    data class SignupError(val message: String) : RemoteObtainingCreateUser()
    data class NetworkError(val message: String) : RemoteObtainingCreateUser()
}