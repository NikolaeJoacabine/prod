package com.nikol.data.remote.network

import com.nikol.data.remote.models.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface AuthApi {

    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(
        @Field("username") encryptedLogin: String,
        @Field("password") encryptedPassword: String
    ): LoginResponse

    @FormUrlEncoded
    @POST("auth/register")
    suspend fun signup(
        @Field("username") encryptedLogin: String,
        @Field("password") encryptedPassword: String
    ): LoginResponse
}