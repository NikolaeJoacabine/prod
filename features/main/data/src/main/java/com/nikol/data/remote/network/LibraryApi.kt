package com.nikol.data.remote.network

import com.nikol.data.remote.models.SuccessMessage
import com.nikol.data.remote.models.MovieDTO
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.DELETE
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
    @POST("/watchlist/add-with-image")
    suspend fun addImage(
        @Part image: MultipartBody.Part,
        @Header("Authorization") authToken: String,
        @Part("film") film: RequestBody
    ): SuccessMessage


    @GET("/films/")
    suspend fun searchFilms(
        @Query("search") string: String,
        @Header("Authorization") authToken: String
    ): List<MovieDTO>

    @POST("/watchlist/add/{film_id}")
    suspend fun addFilm(
        @Path("film_id") id: Int,
        @Header("Authorization") authToken: String
    )

    @GET("/films/{film_id}")
    suspend fun getFilm(
        @Path("film_id") id: Int,
        @Header("Authorization") authToken: String
    ): MovieDTO

    @DELETE("/watchlist/remove/{film_id}")
    suspend fun deleteFilm(
        @Path("film_id") id: Int,
        @Header("Authorization") authToken: String
    )

    @POST("/watched/add/{film_id}")
    suspend fun addInWatched(
        @Path("film_id") id: Int,
        @Header("Authorization") authToken: String
    )

    @GET("/films/discuss/{film_name}/{year}")
    suspend fun getTopics(
        @Path("film_name") name: String,
        @Path("year") year: Int,
        @Header("Authorization") authToken: String
    ): List<String>
}