package com.nikol.data.remote.network

import com.nikol.data.remote.models.Movie

interface LibraryApi {
    suspend fun getLibrary(): List<Movie>
    suspend fun addInLibrary()
    suspend fun deleteMovie()
}