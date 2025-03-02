package com.nikol.domain.use_cases

import com.nikol.domain.repository.SessionFeatureRepository

class GetLikedMoviesUseCase(private val sessionFeatureRepository: SessionFeatureRepository) {
    suspend fun invoke() = sessionFeatureRepository.getLikedMovies()
}