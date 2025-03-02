package com.nikol.data.remote.repository

import com.nikol.data.remote.models.GenreDTO
import com.nikol.data.remote.models.ListGenresDTO
import com.nikol.data.remote.models.SessionMovieDTO
import com.nikol.data.remote.network.SessionApi
import com.nikol.data.utils.toDDomain
import com.nikol.domain.model.Genre

import com.nikol.domain.repository.AuthFeatureRepository
import com.nikol.domain.results.RemoteObtainingGenres
import com.nikol.domain.results.RemoteObtainingMovies
import com.nikol.domain.results.RemoteObtainingSession
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import kotlin.math.log

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

    override suspend fun getLikedMovies(): RemoteObtainingMovies {
        TODO("Not yet implemented")
    }

    override suspend fun getCommonGenres(): RemoteObtainingGenres {
        TODO("Not yet implemented")
    }
}