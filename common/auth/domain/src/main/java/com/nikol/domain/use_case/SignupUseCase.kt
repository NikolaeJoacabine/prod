package com.nikol.domain.use_case

import com.nikol.domain.repository.AuthFeatureRepository

class SignupUseCase(private val authFeatureRepository: AuthFeatureRepository) {
    suspend fun invoke(email: String, password: String) =
        authFeatureRepository.signup(email, password)
}