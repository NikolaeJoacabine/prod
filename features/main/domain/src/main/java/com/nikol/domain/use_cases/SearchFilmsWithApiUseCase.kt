package com.nikol.domain.use_cases

import com.nikol.domain.repository.MainFeatureRepository

class SearchFilmsWithApiUseCase(private val mainFeatureRepository: MainFeatureRepository) {

    suspend fun invoke(str: String) = mainFeatureRepository.searchMoviesWithApi(str)
}