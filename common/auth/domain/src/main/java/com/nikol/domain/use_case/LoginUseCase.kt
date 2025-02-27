package com.nikol.domain.use_case

import com.nikol.domain.repository.AuthFeatureRepository

class LoginUseCase(private val authFeatureRepository: AuthFeatureRepository) {
    suspend fun invoke(email: String, password: String) =
        authFeatureRepository.login(email, password)
}