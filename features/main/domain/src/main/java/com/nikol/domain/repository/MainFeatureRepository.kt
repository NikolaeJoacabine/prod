package com.nikol.domain.repository

import com.nikol.domain.results.RemoteObtainingLibrary
import com.nikol.domain.results.RemoteObtainingLibraryActionResult

interface MainFeatureRepository {
    suspend fun getUserLibrary(): RemoteObtainingLibrary
    suspend fun addMovieInLibrary(): RemoteObtainingLibraryActionResult
    suspend fun deleteMovie(): RemoteObtainingLibraryActionResult
}