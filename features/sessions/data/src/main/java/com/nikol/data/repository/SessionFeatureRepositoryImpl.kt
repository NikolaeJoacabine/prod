package com.nikol.data.repository

import com.nikol.data.remote.repository.RemoteSessionRepository
import com.nikol.domain.repository.SessionFeatureRepository
import com.nikol.domain.results.RemoteObtainingAddResult
import com.nikol.domain.results.RemoteObtainingSession

class SessionFeatureRepositoryImpl(
    private val remoteSessionRepository: RemoteSessionRepository
) : SessionFeatureRepository  {
    override suspend fun addUserIntoSession(login: String, genres: List<String>): RemoteObtainingSession {
        return remoteSessionRepository.addIntoSession(login, genres)
    }

    override suspend fun addMovie(id: Int): RemoteObtainingAddResult {
        return remoteSessionRepository.addMovie(id)
    }
}