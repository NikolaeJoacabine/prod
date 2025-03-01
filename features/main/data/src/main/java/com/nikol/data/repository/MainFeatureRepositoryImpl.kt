package com.nikol.data.repository

import com.nikol.domain.repository.MainFeatureRepository
import com.nikol.domain.results.RemoteObtainingLibrary
import com.nikol.domain.results.RemoteObtainingLibraryActionResult

class MainFeatureRepositoryImpl : MainFeatureRepository {
    override suspend fun getUserLibrary(token: String): RemoteObtainingLibrary {
        TODO("Not yet implemented")
    }

    override suspend fun addMovieInLibrary(token: String): RemoteObtainingLibraryActionResult {
        TODO("Not yet implemented")
    }

    override suspend fun deleteMovie(token: String): RemoteObtainingLibraryActionResult {
        TODO("Not yet implemented")
    }
}