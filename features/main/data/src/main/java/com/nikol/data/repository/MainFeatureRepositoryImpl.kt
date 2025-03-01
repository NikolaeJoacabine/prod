package com.nikol.data.repository

import com.nikol.data.remote.repository.RemoteLibraryRepository
import com.nikol.domain.repository.MainFeatureRepository
import com.nikol.domain.results.RemoteObtainingLibrary
import com.nikol.domain.results.RemoteObtainingLibraryActionResult

class MainFeatureRepositoryImpl(
    private val remoteLibraryRepository: RemoteLibraryRepository
) : MainFeatureRepository {
    override suspend fun getUserLibrary(): RemoteObtainingLibrary {
        TODO("Not yet implemented")
    }

    override suspend fun addMovieInLibrary(): RemoteObtainingLibraryActionResult {
        TODO("Not yet implemented")
    }

    override suspend fun deleteMovie(): RemoteObtainingLibraryActionResult {
        TODO("Not yet implemented")
    }
}