package com.nikol.data.remote.network

import com.nikol.domain.model.Movie

interface SessionApi {
    suspend fun addUserIntoSession()
    suspend fun getLikedMovies(): List<Movie>
}