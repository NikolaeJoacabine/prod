package com.nikol.data.remote.repository

import com.nikol.data.remote.models.Movie
import com.nikol.domain.results.RemoteObtainingLibrary
import com.nikol.domain.results.RemoteObtainingLibraryActionResult

interface RemoteLibraryRepository {
    suspend fun getLibrary(): RemoteObtainingLibrary
    suspend fun addInLibrary(): RemoteObtainingLibraryActionResult
    suspend fun deleteMovie(): RemoteObtainingLibraryActionResult
}