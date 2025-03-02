package com.nikol.data.remote.repository

import com.nikol.data.remote.models.GenreDTO
import com.nikol.domain.model.Genre
import com.nikol.domain.results.RemoteObtainingGenres
import com.nikol.domain.results.RemoteObtainingMovies
import com.nikol.domain.results.RemoteObtainingSession

interface RemoteSessionRepository {
    suspend fun addIntoSession(login:String, genres: List<String>): RemoteObtainingSession
    suspend fun getLikedMovies(): RemoteObtainingMovies
    suspend fun getCommonGenres(): RemoteObtainingGenres
}