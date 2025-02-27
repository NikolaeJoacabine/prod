package com.nikol.domain.use_case

import com.nikol.domain.repository.AuthFeatureRepository

class GetCurrentUserUseCase(private val authFeatureRepository: AuthFeatureRepository) {
    suspend fun invoke() = authFeatureRepository.getCurrentUser()
}