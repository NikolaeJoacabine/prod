package com.nikol.data.remote.repository

import com.nikol.data.remote.models.ListGenresDTO
import com.nikol.data.remote.network.SessionApi
import com.nikol.data.utils.toDDomain
import com.nikol.domain.repository.AuthFeatureRepository
import com.nikol.domain.results.RemoteObtainingAddResult
import com.nikol.domain.results.RemoteObtainingSession

class RemoteSessionRepositoryImpl(
    private val sessionApi: SessionApi,
    private val authFeatureRepository: AuthFeatureRepository
) : RemoteSessionRepository  {

    private val authToken = "Bearer ${authFeatureRepository.getToken()}"

    override suspend fun addIntoSession(login:String, genres: List<String>): RemoteObtainingSession {
        return try {
            val requestBody = ListGenresDTO(genres)
            val result = sessionApi.addUserIntoSession(
                authToken = authToken,
                login,
                requestBody = requestBody
            )
            RemoteObtainingSession.Success(result.map { it.toDDomain() })
        } catch (e: Exception) {
            RemoteObtainingSession.Error("Ошибка ${e.message}")
        }
    }
    override suspend fun addMovie(id: Int): RemoteObtainingAddResult {
        return try {
            val result = sessionApi.addMovie(
                id,
                authToken = authToken
            )
            RemoteObtainingAddResult.Success
        } catch (e: Exception) {
            RemoteObtainingAddResult.Error("Ошибка ${e.message}")
        }
    }
}
