package com.nikol.domain.repository

import com.nikol.domain.model.Movie
import com.nikol.domain.results.RemoteObtainingLibrary
import com.nikol.domain.results.RemoteObtainingLibraryActionResult
import com.nikol.domain.results.RemoteObtainingMovie

interface MainFeatureRepository {
    suspend fun getUserLibrary(): RemoteObtainingLibrary
    suspend fun addMovieInLibrary(id : Int): RemoteObtainingLibraryActionResult
    suspend fun deleteMovie(): RemoteObtainingLibraryActionResult
    suspend fun addNewMovieInLibrary(byte: ByteArray): RemoteObtainingLibraryActionResult
    suspend fun searchMoviesWithApi(str: String): RemoteObtainingLibrary
    suspend fun getDetailMovie(movie: Movie) : RemoteObtainingMovie
}