package com.nikol.data.remote.repository

import com.nikol.data.remote.models.MovieDTO
import com.nikol.domain.model.Movie
import com.nikol.domain.results.RemoteObtainingLibrary
import com.nikol.domain.results.RemoteObtainingLibraryActionResult
import com.nikol.domain.results.RemoteObtainingMovie
import com.nikol.domain.results.RemoteObtainingTopics

interface RemoteLibraryRepository {
    suspend fun getLibrary(): RemoteObtainingLibrary
    suspend fun addInLibrary(id: Int): RemoteObtainingLibraryActionResult
    suspend fun deleteMovie(id: Int): RemoteObtainingLibraryActionResult
    suspend fun addNewFilm(byte: ByteArray, movie: Movie): RemoteObtainingLibraryActionResult
    suspend fun searchFilms(str: String): RemoteObtainingLibrary
    suspend fun getDetailMovie(movieDTO: MovieDTO): RemoteObtainingMovie
    suspend fun addInWatched(id: Int): RemoteObtainingLibraryActionResult
    suspend fun getTopics(name: String, year: Int): RemoteObtainingTopics
}