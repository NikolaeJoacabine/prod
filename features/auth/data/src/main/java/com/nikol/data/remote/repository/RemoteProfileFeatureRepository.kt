package com.nikol.data.remote.repository

import com.nikol.domain.respons.RemoteObtainingUserProfile

interface RemoteProfileFeatureRepository {
    suspend fun getProfile() : RemoteObtainingUserProfile
}