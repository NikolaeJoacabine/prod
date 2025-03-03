package com.nikol.data.repository

import com.nikol.data.remote.repository.RemoteAuthFeatureRepository
import com.nikol.data.remote.repository.RemoteProfileFeatureRepository
import com.nikol.domain.repository.AuthFeatureRepository
import com.nikol.domain.repository.ProfileFeatureRepository
import com.nikol.domain.respons.RemoteObtainingUserProfile

class ProfileFeatureRepositoryImpl(
    private val remoteProfileFeatureRepository: RemoteProfileFeatureRepository,
    private val remoteAuthFeatureRepository: AuthFeatureRepository
) : ProfileFeatureRepository {
    override suspend fun getProfile(): RemoteObtainingUserProfile {
        return remoteProfileFeatureRepository.getProfile()
    }

    override suspend fun exit() {
        remoteAuthFeatureRepository.logout()
    }
}