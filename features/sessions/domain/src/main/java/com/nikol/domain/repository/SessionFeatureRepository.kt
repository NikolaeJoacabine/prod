package com.nikol.domain.repository

import com.nikol.domain.results.RemoteObtainingGenres
import com.nikol.domain.results.RemoteObtainingMovies
import com.nikol.domain.results.RemoteObtainingSession

interface SessionFeatureRepository {
    suspend fun addUserIntoSession(): RemoteObtainingSession
    suspend fun getLikedMovies(): RemoteObtainingMovies
    suspend fun getCommonGenres(): RemoteObtainingGenres
}