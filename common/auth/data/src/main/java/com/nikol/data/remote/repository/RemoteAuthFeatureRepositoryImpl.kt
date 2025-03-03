package com.nikol.data.remote.repository

import android.util.Log
import com.nikol.data.remote.network.AuthApi
import com.nikol.domain.results.RemoteObtainingCreateUser
import com.nikol.domain.results.RemoteObtainingLoginResult
import kotlinx.serialization.SerializationException
import retrofit2.HttpException
import java.io.IOException
import java.util.Base64

class RemoteAuthFeatureRepositoryImpl(
    private val authApi: AuthApi
) : RemoteAuthFeatureRepository {

    override suspend fun signup(login: String, password: String): RemoteObtainingCreateUser {
        return try {
            val response = authApi.signup(login, password)
            Log.d("AuthDebug", "Шаг 1: Ответ получен: $response")
            RemoteObtainingCreateUser.Success(response.accessToken)
        } catch (e: SerializationException) {
            Log.e("AuthError", "Ошибка десериализации: ${e.message}")
            RemoteObtainingCreateUser.NetworkError("Некорректный ответ сервера: ${e.message}")
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string() ?: "Нет тела ошибки"
            Log.e("AuthError", "Ошибка сервера: $errorBody")
            RemoteObtainingCreateUser.SignupError("Ошибка регистрации: $errorBody")
        } catch (e: IOException) {
            Log.e("AuthError", "Сетевая ошибка: ${e.message}")
            RemoteObtainingCreateUser.NetworkError("Сетевая ошибка: ${e.message}")
        } catch (e: Exception) {
            Log.e("AuthError", "Неизвестная ошибка: ${e.message}")
            RemoteObtainingCreateUser.NetworkError("Неизвестная ошибка: ${e.message}")
        }
    }

    override suspend fun login(login: String, password: String): RemoteObtainingLoginResult {
        return try {
            val response = authApi.login(login, password)
            Log.d("AuthDebug", "Шаг 1: Ответ получен: $response")
            RemoteObtainingLoginResult.Success(response.accessToken)
        } catch (e: SerializationException) {
            Log.e("AuthError", "Ошибка десериализации: ${e.message}")
            RemoteObtainingLoginResult.NetworkError("Некорректный ответ сервера: ${e.message}")
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string() ?: "Нет тела ошибки"
            Log.e("AuthError", "Ошибка сервера: $errorBody")
            RemoteObtainingLoginResult.LoginError("Ошибка авторизации: $errorBody")
        } catch (e: IOException) {
            Log.e("AuthError", "Сетевая ошибка: ${e.message}")
            RemoteObtainingLoginResult.NetworkError("Сетевая ошибка: ${e.message}")
        } catch (e: Exception) {
            Log.e("AuthError", "Неизвестная ошибка: ${e.message}")
            RemoteObtainingLoginResult.NetworkError("Неизвестная ошибка: ${e.message}")
        }
    }
}
