package com.nikol.data.remote.repository

import com.nikol.data.remote.network.UserApi
import com.nikol.data.utils.toDomain
import com.nikol.domain.repository.AuthFeatureRepository
import com.nikol.domain.respons.RemoteObtainingUserProfile

class RemoteProfileFeatureRepositoryImpl(
    private val userApi: UserApi,
    private val authFeatureRepository: AuthFeatureRepository
) : RemoteProfileFeatureRepository {
    private val authToken = "Bearer ${authFeatureRepository.getToken()}"

    override suspend fun getProfile(): RemoteObtainingUserProfile {
        return try {
            val response = userApi.getProfile(authToken)
            RemoteObtainingUserProfile.Success(response.toDomain())
        } catch (e: Exception) {
            RemoteObtainingUserProfile.Error(e.message.toString())
        }
    }
}