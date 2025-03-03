package com.nikol.domain.use_case

import com.nikol.domain.repository.ProfileFeatureRepository

class GetProfileUseCase(private val profileFeatureRepository: ProfileFeatureRepository) {
    suspend fun invoke() = profileFeatureRepository.getProfile()
}