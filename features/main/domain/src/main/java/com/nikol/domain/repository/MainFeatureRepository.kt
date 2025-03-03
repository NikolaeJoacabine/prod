package com.nikol.domain.repository

import com.nikol.domain.model.Movie
import com.nikol.domain.results.RemoteObtainingLibrary
import com.nikol.domain.results.RemoteObtainingLibraryActionResult
import com.nikol.domain.results.RemoteObtainingMovie
import com.nikol.domain.results.RemoteObtainingTopics

interface MainFeatureRepository {
    suspend fun getUserLibrary(): RemoteObtainingLibrary
    suspend fun addMovieInLibrary(id : Int): RemoteObtainingLibraryActionResult
    suspend fun deleteMovie(id: Int): RemoteObtainingLibraryActionResult
    suspend fun addNewMovieInLibrary(byte: ByteArray, movie: Movie): RemoteObtainingLibraryActionResult
    suspend fun searchMoviesWithApi(str: String): RemoteObtainingLibrary
    suspend fun getDetailMovie(movie: Movie) : RemoteObtainingMovie
    suspend fun addInWatched(id: Int): RemoteObtainingLibraryActionResult
    suspend fun getTopics(name: String, year: Int): RemoteObtainingTopics
}