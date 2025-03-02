package com.nikol.data.remote.network

import com.nikol.data.remote.models.ListGenresDTO
import com.nikol.data.remote.models.SessionMovieDTO
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

    @POST("/watchlist/add/{film_id}")
    suspend fun addMovie(
        @Path("film_id") id: Int,
        @Header("Authorization") authToken: String
    )

}