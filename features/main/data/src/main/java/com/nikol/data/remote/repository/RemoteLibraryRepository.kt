package com.nikol.data.remote.repository

import com.nikol.domain.results.RemoteObtainingLibrary
import com.nikol.domain.results.RemoteObtainingLibraryActionResult

interface RemoteLibraryRepository {
    suspend fun getLibrary(): RemoteObtainingLibrary
    suspend fun addInLibrary(id: Int): RemoteObtainingLibraryActionResult
    suspend fun deleteMovie(): RemoteObtainingLibraryActionResult
    suspend fun addNewFilm(byte: ByteArray) : RemoteObtainingLibraryActionResult
    suspend fun searchFilms(str: String): RemoteObtainingLibrary
}