package com.nikol.data.remote.network

import com.nikol.data.remote.models.EncryptionKeyResponse
import com.nikol.data.remote.models.LoginResponse
import com.nikol.data.remote.network.target.NoAuth
import retrofit2.http.Header
import retrofit2.http.POST


interface AuthApi {
    @NoAuth
    @POST("/auth/login")
    suspend fun requestEncryptionKey(): EncryptionKeyResponse

    @NoAuth
    @POST("auth/login")
    suspend fun login(
        @Header("Login") encryptedLogin: String,
        @Header("Password") encryptedPassword: String
    ): LoginResponse

    @NoAuth
    @POST("/auth/register")
    suspend fun requestEncryptionKeyFromSignup(): EncryptionKeyResponse

    @NoAuth
    @POST("auth/register")
    suspend fun signup(
        @Header("Login") encryptedLogin: String,
        @Header("Password") encryptedPassword: String
    ): LoginResponse

}