package com.nikol.data.remote.network

import com.nikol.data.remote.models.SessionMovieDTO

interface SessionApi {
    suspend fun addUserIntoSession()
    suspend fun getLikedMovies(): List<SessionMovieDTO>
}