package com.nikol.domain.use_cases

import com.nikol.domain.repository.MainFeatureRepository

class DeleteMovieUseCase(private val mainFeatureRepository: MainFeatureRepository) {
    suspend fun invoke() =
        mainFeatureRepository.deleteMovie()
}