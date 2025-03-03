package com.nikol.domain.use_cases

import com.nikol.domain.model.Movie
import com.nikol.domain.repository.MainFeatureRepository

class AddNewMovieUseCase(private val mainFeatureRepository: MainFeatureRepository) {
    suspend fun invoke(byte: ByteArray, movie: Movie) = mainFeatureRepository.addNewMovieInLibrary(byte, movie)
}