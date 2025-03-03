package com.nikol.data.repository

import com.nikol.data.remote.repository.RemoteProfileFeatureRepository
import com.nikol.domain.repository.ProfileFeatureRepository
import com.nikol.domain.respons.RemoteObtainingUserProfile

class ProfileFeatureRepositoryImpl(
    private val remoteProfileFeatureRepository: RemoteProfileFeatureRepository
) : ProfileFeatureRepository {
    override suspend fun getProfile() : RemoteObtainingUserProfile {
        return remoteProfileFeatureRepository.getProfile()
    }
}