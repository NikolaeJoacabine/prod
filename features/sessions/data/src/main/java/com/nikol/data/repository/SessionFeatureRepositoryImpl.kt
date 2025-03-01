package com.nikol.data.repository

import com.nikol.data.remote.repository.RemoteSessionRepository
import com.nikol.domain.repository.SessionFeatureRepository
import com.nikol.domain.results.RemoteObtainingMovies
import com.nikol.domain.results.RemoteObtainingSession

class SessionFeatureRepositoryImpl(
    private val remoteLibraryRepository: RemoteSessionRepository
) : SessionFeatureRepository  {
    override suspend fun addUserIntoSession(): RemoteObtainingSession {
        TODO("Not yet implemented")
    }

    override suspend fun getLikedMovies(): RemoteObtainingMovies {
        TODO("Not yet implemented")
    }
}