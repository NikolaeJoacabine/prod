package com.nikol.data.remote.repository

import com.nikol.domain.results.RemoteObtainingMovies
import com.nikol.domain.results.RemoteObtainingSession

interface RemoteSessionRepository {
    suspend fun addIntoSession(): RemoteObtainingSession
    suspend fun getLikedMovies(): RemoteObtainingMovies
}