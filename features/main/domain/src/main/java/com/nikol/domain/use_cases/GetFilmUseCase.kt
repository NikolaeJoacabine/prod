package com.nikol.domain.use_cases

import com.nikol.domain.model.Movie
import com.nikol.domain.repository.MainFeatureRepository

class GetFilmUseCase(private val mainFeatureRepository: MainFeatureRepository) {
    suspend fun invoke(movie: Movie) = mainFeatureRepository.getDetailMovie(movie)
}