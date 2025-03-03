package com.nikol.data.remote.repository

import com.nikol.domain.results.RemoteObtainingAddResult
import com.nikol.domain.results.RemoteObtainingSession

interface RemoteSessionRepository {
    suspend fun addIntoSession(login:String, genres: List<String>): RemoteObtainingSession
    suspend fun addMovie(id: Int): RemoteObtainingAddResult
}