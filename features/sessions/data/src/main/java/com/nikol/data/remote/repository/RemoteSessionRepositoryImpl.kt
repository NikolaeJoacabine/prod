package com.nikol.data.remote.repository

import com.nikol.data.remote.network.SessionApi
import com.nikol.domain.repository.AuthFeatureRepository
import com.nikol.domain.results.RemoteObtainingMovies
import com.nikol.domain.results.RemoteObtainingSession

class RemoteSessionRepositoryImpl(
    private val libraryApi: SessionApi,
    private val authFeatureRepository: AuthFeatureRepository
) : RemoteSessionRepository  {
    override suspend fun addIntoSession(): RemoteObtainingSession {
        TODO("Not yet implemented")
    }

    override suspend fun getLikedMovies(): RemoteObtainingMovies {
        TODO("Not yet implemented")
    }
}