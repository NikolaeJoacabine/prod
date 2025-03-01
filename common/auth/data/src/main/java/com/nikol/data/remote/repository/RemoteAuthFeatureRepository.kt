package com.nikol.data.remote.repository

import com.nikol.domain.results.RemoteObtainingCreateUser
import com.nikol.domain.results.RemoteObtainingLoginResult

interface RemoteAuthFeatureRepository {
    suspend fun login(login: String, password: String): RemoteObtainingLoginResult
    suspend fun signup(login: String, password: String): RemoteObtainingCreateUser
}