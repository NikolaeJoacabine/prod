package com.nikol.domain.use_cases

import com.nikol.domain.repository.SessionFeatureRepository

class AddMovieIntoFavoriteUseCase(private val sessionFeatureRepository: SessionFeatureRepository) {
    suspend fun invoke(id: Int) = sessionFeatureRepository.addMovie(id)
}