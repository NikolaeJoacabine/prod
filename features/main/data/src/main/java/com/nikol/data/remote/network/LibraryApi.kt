package com.nikol.data.remote.network

import com.nikol.data.remote.models.ImageUrl
import com.nikol.data.remote.models.MovieDTO
import dagger.Module
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface LibraryApi {

    @GET("/watchlist/")
    suspend fun getLibrary(
        @Header("Authorization") authToken: String
    ): List<MovieDTO>


    @Multipart
    @POST("/films/add-image")
    suspend fun addImage(
        @Part image: MultipartBody.Part,
        @Header("Authorization") authToken: String
    ): ImageUrl


    @GET("/films/")
    suspend fun searchFilms(
        @Query("search") string: String,
        @Header("Authorization") authToken: String
    ): List<MovieDTO>

}