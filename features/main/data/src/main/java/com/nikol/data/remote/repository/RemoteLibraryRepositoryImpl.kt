package com.nikol.data.remote.repository

import com.nikol.data.remote.models.Movie
import com.nikol.data.remote.network.LibraryApi
import com.nikol.domain.repository.AuthFeatureRepository
import com.nikol.domain.results.RemoteObtainingLibrary
import com.nikol.domain.results.RemoteObtainingLibraryActionResult

class RemoteLibraryRepositoryImpl(
    private val libraryApi: LibraryApi,
    private val authFeatureRepository: AuthFeatureRepository
) : RemoteLibraryRepository {
    override suspend fun getLibrary(): RemoteObtainingLibrary {
        TODO("Not yet implemented")
    }

    override suspend fun addInLibrary(): RemoteObtainingLibraryActionResult {
        TODO("Not yet implemented")
    }

    override suspend fun deleteMovie(): RemoteObtainingLibraryActionResult {
        TODO("Not yet implemented")
    }
}