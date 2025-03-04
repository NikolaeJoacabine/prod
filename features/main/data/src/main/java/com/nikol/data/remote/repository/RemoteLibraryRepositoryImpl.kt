package com.nikol.data.remote.repository

import android.util.Log
import com.nikol.data.remote.models.MovieDTO
import com.nikol.data.remote.network.LibraryApi
import com.nikol.data.utils.toDomain
import com.nikol.data.utils.toEntity
import com.nikol.domain.model.Movie
import com.nikol.domain.repository.AuthFeatureRepository
import com.nikol.domain.results.RemoteObtainingLibrary
import com.nikol.domain.results.RemoteObtainingLibraryActionResult
import com.nikol.domain.results.RemoteObtainingMovie
import com.nikol.domain.results.RemoteObtainingTopics
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody

class RemoteLibraryRepositoryImpl(
    private val libraryApi: LibraryApi,
    private val authFeatureRepository: AuthFeatureRepository
) : RemoteLibraryRepository {


    override suspend fun getLibrary(): RemoteObtainingLibrary {
        return try {
            val result = libraryApi.getLibrary(
                authToken = authFeatureRepository.getToken()
            )
            RemoteObtainingLibrary.Success(result.map { it.toDomain() })
        } catch (e: Exception) {
            RemoteObtainingLibrary.Error("Ошибка ${e.message}")
        }
    }

    override suspend fun addInLibrary(id: Int): RemoteObtainingLibraryActionResult {
        return try {
            val response = libraryApi.addFilm(id, authFeatureRepository.getToken())
            RemoteObtainingLibraryActionResult.Success
        } catch (e: Exception) {
            RemoteObtainingLibraryActionResult.Error("${e.message}")
        }
    }

    override suspend fun deleteMovie(id: Int): RemoteObtainingLibraryActionResult {
        return try {
            val response = libraryApi.deleteFilm(id, authFeatureRepository.getToken())
            RemoteObtainingLibraryActionResult.Success
        } catch (e: Exception) {
            RemoteObtainingLibraryActionResult.Error("${e.message}")
        }
    }

    override suspend fun addNewFilm(
        byte: ByteArray,
        movie: Movie
    ): RemoteObtainingLibraryActionResult {
        return try {


            val requestBody = byte.toRequestBody("image/jpeg".toMediaType())
            val imagePart = MultipartBody.Part.createFormData("file", "image.jpg", requestBody)

            val filmJson = Json.encodeToString(movie.toEntity())
            val filmPart = filmJson.toRequestBody("application/json".toMediaTypeOrNull())

            val result = libraryApi.addImage(
                image = imagePart,
                authToken = authFeatureRepository.getToken(),
                film = filmPart
            )
            RemoteObtainingLibraryActionResult.Success
        } catch (e: Exception) {

            Log.e("Image", "Error uploading image: ${e.message}", e)
            RemoteObtainingLibraryActionResult.Error("Ошибка загрузки изображения: ${e.message ?: "Неизвестная ошибка"}")
        }
    }

    override suspend fun searchFilms(str: String): RemoteObtainingLibrary {
        return try {
            val response = libraryApi.searchFilms(str, authFeatureRepository.getToken())

            RemoteObtainingLibrary.Success(response.map { it.toDomain() })
        } catch (e: Exception) {
            Log.e("Search", "Error: ${e.message}")
            RemoteObtainingLibrary.Error(e.message.toString())
        }
    }

    override suspend fun getDetailMovie(movieDTO: MovieDTO): RemoteObtainingMovie {
        return try {
            val response = libraryApi.getFilm(movieDTO.id ?: 0, authFeatureRepository.getToken())
            RemoteObtainingMovie.Success(response.toDomain())
        } catch (e: Exception) {
            RemoteObtainingMovie.Error(e.message.toString())
        }
    }

    override suspend fun addInWatched(id: Int): RemoteObtainingLibraryActionResult {
        return try {
            val response = libraryApi.addInWatched(id, authFeatureRepository.getToken())
            RemoteObtainingLibraryActionResult.Success
        } catch (e: Exception) {
            RemoteObtainingLibraryActionResult.Error(e.message.toString())
        }
    }

    override suspend fun getTopics(name: String, year: Int): RemoteObtainingTopics {
        return try {
            val response = libraryApi.getTopics(name, year, authFeatureRepository.getToken())
            RemoteObtainingTopics.Success(response)
        } catch (e: Exception) {
            RemoteObtainingTopics.Error(e.message.toString())
        }
    }
}