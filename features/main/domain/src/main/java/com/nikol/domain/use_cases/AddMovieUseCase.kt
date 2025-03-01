package com.nikol.domain.use_cases

import com.nikol.domain.repository.MainFeatureRepository

class AddMovieUseCase(private val mainFeatureRepository: MainFeatureRepository) {
    suspend fun invoke(token: String) =
        mainFeatureRepository.addMovieInLibrary(token)
}