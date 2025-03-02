package com.nikol.domain.repository

import com.nikol.domain.results.RemoteObtainingLibrary
import com.nikol.domain.results.RemoteObtainingLibraryActionResult

interface MainFeatureRepository {
    suspend fun getUserLibrary(): RemoteObtainingLibrary
    suspend fun addMovieInLibrary(id : Int): RemoteObtainingLibraryActionResult
    suspend fun deleteMovie(): RemoteObtainingLibraryActionResult
    suspend fun addNewMovieInLibrary(byte: ByteArray): RemoteObtainingLibraryActionResult
    suspend fun searchMoviesWithApi(str: String): RemoteObtainingLibrary
}