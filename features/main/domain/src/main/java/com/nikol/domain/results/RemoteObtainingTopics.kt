package com.nikol.domain.results

import com.nikol.domain.model.Movie

sealed class RemoteObtainingTopics {
    data class Success(val topics: List<String>) : RemoteObtainingTopics()
    data object Loading : RemoteObtainingTopics()
    data object Neutral : RemoteObtainingTopics()
    data class Error(val message: String) : RemoteObtainingTopics()
}