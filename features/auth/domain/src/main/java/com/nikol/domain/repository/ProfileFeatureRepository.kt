package com.nikol.domain.repository

import com.nikol.domain.respons.RemoteObtainingUserProfile

interface ProfileFeatureRepository {
    suspend fun getProfile() : RemoteObtainingUserProfile
    suspend fun exit()
}