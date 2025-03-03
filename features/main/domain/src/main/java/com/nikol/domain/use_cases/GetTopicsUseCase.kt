package com.nikol.domain.use_cases

import com.nikol.domain.model.Movie
import com.nikol.domain.repository.MainFeatureRepository

class GetTopicsUseCase(private val mainFeatureRepository: MainFeatureRepository) {
    suspend fun invoke(name: String, year: Int) = mainFeatureRepository.getTopics(name, year)
}