package com.nikol.domain.repository

import com.nikol.domain.results.RemoteObtainingAddResult
import com.nikol.domain.results.RemoteObtainingSession

interface SessionFeatureRepository {
    suspend fun addUserIntoSession(login: String, genres: List<String>): RemoteObtainingSession
    suspend fun addMovie(id: Int): RemoteObtainingAddResult
}