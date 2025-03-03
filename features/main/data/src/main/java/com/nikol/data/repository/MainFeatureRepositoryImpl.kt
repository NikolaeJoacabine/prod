package com.nikol.data.repository

import android.util.Log
import com.nikol.data.remote.repository.RemoteLibraryRepository
import com.nikol.data.utils.toEntity
import com.nikol.domain.model.Movie
import com.nikol.domain.repository.MainFeatureRepository
import com.nikol.domain.results.RemoteObtainingLibrary
import com.nikol.domain.results.RemoteObtainingLibraryActionResult
import com.nikol.domain.results.RemoteObtainingMovie
import com.nikol.domain.results.RemoteObtainingTopics

class MainFeatureRepositoryImpl(
    private val remoteLibraryRepository: RemoteLibraryRepository
) : MainFeatureRepository {
    override suspend fun getUserLibrary(): RemoteObtainingLibrary {
        return remoteLibraryRepository.getLibrary()
    }

    override suspend fun addMovieInLibrary(id: Int): RemoteObtainingLibraryActionResult {
        return remoteLibraryRepository.addInLibrary(id)

    }

    override suspend fun deleteMovie(id: Int): RemoteObtainingLibraryActionResult {
        return remoteLibraryRepository.deleteMovie(id)
    }

    override suspend fun addNewMovieInLibrary(byte: ByteArray, movie: Movie): RemoteObtainingLibraryActionResult {
        return remoteLibraryRepository.addNewFilm(byte,movie)
    }

    override suspend fun searchMoviesWithApi(str: String): RemoteObtainingLibrary {
        return remoteLibraryRepository.searchFilms(str)
    }

    override suspend fun getDetailMovie(movie: Movie): RemoteObtainingMovie {
        return remoteLibraryRepository.getDetailMovie(movie.toEntity())
    }

    override suspend fun addInWatched(id: Int): RemoteObtainingLibraryActionResult {
        return remoteLibraryRepository.addInWatched(id)
    }

    override suspend fun getTopics(name: String, year: Int): RemoteObtainingTopics {
        return remoteLibraryRepository.getTopics(name, year)
    }
}