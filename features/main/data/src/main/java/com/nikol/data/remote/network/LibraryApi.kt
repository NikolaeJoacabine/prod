package com.nikol.data.remote.network

import com.nikol.data.remote.models.MovieDTO

interface LibraryApi {
    suspend fun getLibrary(): List<MovieDTO>
    suspend fun addInLibrary()
    suspend fun deleteMovie()
}