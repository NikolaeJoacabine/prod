package com.nikol.domain.use_cases

import com.nikol.domain.repository.SessionFeatureRepository

class AddUserIntoSessionUseCase(private val sessionFeatureRepository: SessionFeatureRepository) {
    suspend fun invoke(login: String, genres: List<String>) = sessionFeatureRepository.addUserIntoSession(login, genres)
}