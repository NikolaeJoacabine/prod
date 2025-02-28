package com.nikol.data.reppository

import com.nikol.data.local.repository.LocalAuthFeaturesRepository
import com.nikol.data.remote.repository.RemoteAuthFeatureRepository
import com.nikol.domain.repository.AuthFeatureRepository
import com.nikol.domain.results.RemoteObtainingCreateUser
import com.nikol.domain.results.RemoteObtainingLoginResult

class AuthFeatureRepositoryImpl(
    private val remoteRepository: RemoteAuthFeatureRepository,
    private val localRepository: LocalAuthFeaturesRepository
) : AuthFeatureRepository {

    private var accessToken: String? = null

    override suspend fun signup(email: String, password: String): RemoteObtainingCreateUser {
        val result = remoteRepository.signup(email, password)
        if (result is RemoteObtainingCreateUser.Success) {
            accessToken = result.currentToken
            localRepository.saveCredentials(email, password)
        }
        return result
    }

    override suspend fun login(email: String, password: String): RemoteObtainingLoginResult {
        val result = remoteRepository.login(email, password)
        if (result is RemoteObtainingLoginResult.Success) {
            accessToken = result.currentToken
            localRepository.saveCredentials(email, password)
        }
        return result
    }

    override suspend fun logout() {
        accessToken = null
        localRepository.clearCredentials()
    }

    override suspend fun getCurrentUser(): Pair<String, String>? {
        return localRepository.getCredentials()
    }

    override fun getToken(): String? = accessToken
}