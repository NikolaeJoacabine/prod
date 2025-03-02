package com.nikol.data.remote.repository

import android.util.Log
import com.nikol.data.remote.models.MovieDTO
import com.nikol.data.remote.network.LibraryApi
import com.nikol.data.utils.toDomain
import com.nikol.domain.repository.AuthFeatureRepository
import com.nikol.domain.results.RemoteObtainingLibrary
import com.nikol.domain.results.RemoteObtainingLibraryActionResult
import com.nikol.domain.results.RemoteObtainingMovie
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody

class RemoteLibraryRepositoryImpl(
    private val libraryApi: LibraryApi,
    private val authFeatureRepository: AuthFeatureRepository
) : RemoteLibraryRepository {

    private val authToken = "Bearer ${authFeatureRepository.getToken()}"
    override suspend fun getLibrary(): RemoteObtainingLibrary {
        return try {
            val result = libraryApi.getLibrary(
                authToken = authToken
            )
            RemoteObtainingLibrary.Success(result.map { it.toDomain() })
        } catch (e: Exception) {
            RemoteObtainingLibrary.Error("Ошибка ${e.message}")
        }
    }

    override suspend fun addInLibrary(id: Int): RemoteObtainingLibraryActionResult {
        return try {
            val response = libraryApi.addFilm(id, authToken)
            RemoteObtainingLibraryActionResult.Success
        } catch (e: Exception) {
            RemoteObtainingLibraryActionResult.Error("${e.message}")
        }
    }

    override suspend fun deleteMovie(): RemoteObtainingLibraryActionResult {
        TODO("Not yet implemented")
    }

    override suspend fun addNewFilm(byte: ByteArray): RemoteObtainingLibraryActionResult {
        return try {
            val requestBody = byte.toRequestBody("image/jpeg".toMediaType())

            val imagePart = MultipartBody.Part.createFormData("file", "image.jpg", requestBody)
            val result = libraryApi.addImage(
                image = imagePart,
                authToken = authToken
            )
            Log.d(
                "Image", "Image URL: ${
                    result.image
                }"
            )
            RemoteObtainingLibraryActionResult.Success
        } catch (e: Exception) {

            Log.e("Image", "Error uploading image: ${e.message}", e)
            RemoteObtainingLibraryActionResult.Error("Ошибка загрузки изображения: ${e.message ?: "Неизвестная ошибка"}")
        }
    }

    override suspend fun searchFilms(str: String): RemoteObtainingLibrary {
        return try {
            val response = libraryApi.searchFilms(str, authToken)

            RemoteObtainingLibrary.Success(response.map { it.toDomain() })
        } catch (e: Exception) {
            Log.e("Search", "Error: ${e.message}")
            RemoteObtainingLibrary.Error(e.message.toString())
        }
    }

    override suspend fun getDetailMovie(movieDTO: MovieDTO): RemoteObtainingMovie {
        return try {
            val response = libraryApi.getFilm(movieDTO.id ?: 0)
            RemoteObtainingMovie.Success(response.toDomain())
        } catch (e: Exception) {
            RemoteObtainingMovie.Error(e.message.toString())
        }
    }
}