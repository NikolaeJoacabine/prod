package com.nikol.data.local.repository

import kotlinx.coroutines.flow.Flow

interface LocalAuthFeaturesRepository {
    suspend fun saveCredentials(email: String, password: String)
    suspend fun getCredentials(): Flow<Pair<String, String>?>
    suspend fun clearCredentials()
}