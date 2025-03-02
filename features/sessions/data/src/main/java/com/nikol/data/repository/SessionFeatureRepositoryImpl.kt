package com.nikol.data.repository

import com.nikol.data.remote.models.ListGenresDTO
import com.nikol.data.remote.repository.RemoteSessionRepository
import com.nikol.domain.repository.SessionFeatureRepository
import com.nikol.domain.results.RemoteObtainingGenres
import com.nikol.domain.results.RemoteObtainingMovies
import com.nikol.domain.results.RemoteObtainingSession

class SessionFeatureRepositoryImpl(
    private val remoteSessionRepository: RemoteSessionRepository
) : SessionFeatureRepository  {
    override suspend fun addUserIntoSession(login: String, genres: List<String>): RemoteObtainingSession {
        return remoteSessionRepository.addIntoSession(login, genres)
    }

    override suspend fun getLikedMovies(): RemoteObtainingMovies {
        return remoteSessionRepository.getLikedMovies()
    }

    override suspend fun getCommonGenres(): RemoteObtainingGenres {
        return remoteSessionRepository.getCommonGenres()
    }
}