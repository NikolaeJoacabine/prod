package com.nikol.data.local.repository

interface LocalAuthFeaturesRepository {
    suspend fun saveCredentials(email: String, password: String)
    suspend fun getCredentials(): Pair<String, String>?
    suspend fun clearCredentials()
}