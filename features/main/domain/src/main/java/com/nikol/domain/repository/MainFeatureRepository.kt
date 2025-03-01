package com.nikol.domain.repository

import com.nikol.domain.results.RemoteObtainingLibrary
import com.nikol.domain.results.RemoteObtainingLibraryActionResult

interface MainFeatureRepository {
    suspend fun getUserLibrary(token: String): RemoteObtainingLibrary
    suspend fun addMovieInLibrary(token: String): RemoteObtainingLibraryActionResult
    suspend fun deleteMovie(token: String): RemoteObtainingLibraryActionResult
}