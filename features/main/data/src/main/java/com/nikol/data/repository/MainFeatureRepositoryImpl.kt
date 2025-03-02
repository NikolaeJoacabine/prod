package com.nikol.data.repository

import android.util.Log
import com.nikol.data.remote.repository.RemoteLibraryRepository
import com.nikol.domain.repository.MainFeatureRepository
import com.nikol.domain.results.RemoteObtainingLibrary
import com.nikol.domain.results.RemoteObtainingLibraryActionResult

class MainFeatureRepositoryImpl(
    private val remoteLibraryRepository: RemoteLibraryRepository
) : MainFeatureRepository {
    override suspend fun getUserLibrary(): RemoteObtainingLibrary {
        return remoteLibraryRepository.getLibrary()
    }

    override suspend fun addMovieInLibrary(id: Int): RemoteObtainingLibraryActionResult {
        return remoteLibraryRepository.addInLibrary(id)

    }

    override suspend fun deleteMovie(): RemoteObtainingLibraryActionResult {
        return remoteLibraryRepository.deleteMovie()
    }

    override suspend fun addNewMovieInLibrary(byte: ByteArray): RemoteObtainingLibraryActionResult {
        return remoteLibraryRepository.addNewFilm(byte)
    }

    override suspend fun searchMoviesWithApi(str: String): RemoteObtainingLibrary {
        return remoteLibraryRepository.searchFilms(str)
    }
}