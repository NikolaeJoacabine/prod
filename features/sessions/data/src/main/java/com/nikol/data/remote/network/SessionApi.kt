package com.nikol.data.remote.network

import com.nikol.data.remote.models.ListGenresDTO
import com.nikol.data.remote.models.SessionDTO
import com.nikol.data.remote.models.SessionMovieDTO
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface SessionApi {
    @POST("/session/create/{login}")
    suspend fun addUserIntoSession(
        @Header("Authorization") authToken: String,
        @Path("login") login: String,
        @Body requestBody: ListGenresDTO
    ): List<SessionMovieDTO>

    suspend fun getLikedMovies(token: String): List<SessionMovieDTO>
}