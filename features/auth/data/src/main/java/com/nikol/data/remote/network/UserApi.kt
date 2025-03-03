package com.nikol.data.remote.network

import com.nikol.data.remote.models.ProfileDTO
import retrofit2.http.GET
import retrofit2.http.Header

interface UserApi {

    @GET("/profile/")
    suspend fun getProfile(
        @Header("Authorization") authToken: String
    ) : ProfileDTO
}