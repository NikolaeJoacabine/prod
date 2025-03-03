package com.nikol.domain.respons

import com.nikol.domain.models.UserProfile

sealed class RemoteObtainingUserProfile {
    data class Success(val profile: UserProfile) : RemoteObtainingUserProfile()
    data object Loading : RemoteObtainingUserProfile()
    data class Error(val message: String) : RemoteObtainingUserProfile()
}