package com.nikol.domain.repository

import com.nikol.domain.results.RemoteObtainingCreateUser
import com.nikol.domain.results.RemoteObtainingLoginResult
import kotlinx.coroutines.flow.Flow

interface AuthFeatureRepository {
    suspend fun signup(email: String, password: String): RemoteObtainingCreateUser
    suspend fun login(email: String, password: String): RemoteObtainingLoginResult
    suspend fun logout()
    suspend fun getCurrentUser(): Flow<Pair<String, String>?>
    suspend fun getToken(): String
}