package com.nikol.data.reppository

import android.util.Log
import com.nikol.data.local.repository.LocalAuthFeaturesRepository
import com.nikol.data.local.storage.TokenStorage
import com.nikol.data.remote.repository.RemoteAuthFeatureRepository
import com.nikol.domain.repository.AuthFeatureRepository
import com.nikol.domain.results.RemoteObtainingCreateUser
import com.nikol.domain.results.RemoteObtainingLoginResult
import kotlinx.coroutines.flow.Flow

class AuthFeatureRepositoryImpl(
    private val remoteRepository: RemoteAuthFeatureRepository,
    private val localRepository: LocalAuthFeaturesRepository,
    private val tokenStorage: TokenStorage
) : AuthFeatureRepository {

    override suspend fun signup(email: String, password: String): RemoteObtainingCreateUser {
        val result = remoteRepository.signup(email, password)
        if (result is RemoteObtainingCreateUser.Success) {
            tokenStorage.saveToken(result.currentToken)
            localRepository.saveCredentials(email, password)
        }
        return result
    }

    override suspend fun login(email: String, password: String): RemoteObtainingLoginResult {
        val result = remoteRepository.login(email, password)
        if (result is RemoteObtainingLoginResult.Success) {
            tokenStorage.saveToken(result.currentToken)
            localRepository.saveCredentials(email, password)
        }
        return result
    }

    override suspend fun logout() {
        tokenStorage.clearToken()
        localRepository.clearCredentials()
        Log.d("Auth", "All credentials cleared")
    }

    override suspend fun getCurrentUser(): Flow<Pair<String, String>?> {
        return localRepository.getCredentials()
    }

    override suspend fun getToken(): String {
        return "Bearer "+ tokenStorage.getToken()
    }
}