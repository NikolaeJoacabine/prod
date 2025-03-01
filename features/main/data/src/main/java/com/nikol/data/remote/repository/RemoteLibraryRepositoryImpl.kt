package com.nikol.data.remote.repository

import com.nikol.data.remote.models.Movie
import com.nikol.data.remote.network.LibraryApi
import com.nikol.domain.results.RemoteObtainingLibrary
import com.nikol.domain.results.RemoteObtainingLibraryActionResult

class RemoteLibraryRepositoryImpl(libraryApi: LibraryApi) : RemoteLibraryRepository {
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