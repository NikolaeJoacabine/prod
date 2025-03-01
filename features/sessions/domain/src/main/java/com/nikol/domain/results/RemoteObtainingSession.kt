package com.nikol.domain.results

import com.nikol.domain.model.Session

sealed class RemoteObtainingSession {
    data class Success(val session: Session) : RemoteObtainingSession()
    data object Loading : RemoteObtainingSession()
    data object Neutral: RemoteObtainingSession()
    data class Error(val message: String) : RemoteObtainingSession()
}