package com.nikol.data.remote.network

import com.nikol.data.remote.models.EncryptionKeyResponse
import com.nikol.data.remote.models.LoginResponse
import retrofit2.http.Header
import retrofit2.http.POST


interface AuthApi {

    @POST("auth/login")
    suspend fun login(
        @Header("Login") encryptedLogin: String,
        @Header("Password") encryptedPassword: String
    ): LoginResponse

    @POST("auth/register")
    suspend fun signup(
        @Header("Login") encryptedLogin: String,
        @Header("Password") encryptedPassword: String
    ): LoginResponse
}