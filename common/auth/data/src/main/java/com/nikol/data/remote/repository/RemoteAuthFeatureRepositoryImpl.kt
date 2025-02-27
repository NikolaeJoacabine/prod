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
            Log.d("AuthDebug", "Шаг 1: Получение ключа")
            val keyResponse = authApi.requestEncryptionKeyFromSignup()
            Log.d("AuthDebug", "Шаг 2: Ключ получен: ${keyResponse.key}")
            val encryptionKey = keyResponse.key
            Log.d("AuthDebug", "Шаг 3: Шифрование login=$login")
            val encryptedLogin = xorEncrypt(login, encryptionKey)
            Log.d("AuthDebug", "Шаг 4: Зашифрованный login=$encryptedLogin")
            Log.d("AuthDebug", "Шаг 5: Шифрование password=$password")
            val encryptedPassword = xorEncrypt(password, encryptionKey)
            Log.d("AuthDebug", "Шаг 6: Зашифрованный password=$encryptedPassword")
            Log.d("AuthDebug", "Шаг 7: Отправка запроса")
            val response = authApi.signup(encryptedLogin, encryptedPassword)
            Log.d("AuthDebug", "Шаг 8: Ответ получен: $response")
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

    // Аналогично для login
    override suspend fun login(login: String, password: String): RemoteObtainingLoginResult {
        return try {
            Log.d("AuthDebug", "Шаг 1: Получение ключа")
            val keyResponse = authApi.requestEncryptionKey()
            Log.d("AuthDebug", "Шаг 2: Ключ получен: ${keyResponse.key}")
            val encryptionKey = keyResponse.key
            Log.d("AuthDebug", "Шаг 3: Шифрование login=$login")
            val encryptedLogin = xorEncrypt(login, encryptionKey)
            Log.d("AuthDebug", "Шаг 4: Зашифрованный login=$encryptedLogin")
            Log.d("AuthDebug", "Шаг 5: Шифрование password=$password")
            val encryptedPassword = xorEncrypt(password, encryptionKey)
            Log.d("AuthDebug", "Шаг 6: Зашифрованный password=$encryptedPassword")
            Log.d("AuthDebug", "Шаг 7: Отправка запроса")
            val response = authApi.login(encryptedLogin, encryptedPassword)
            Log.d("AuthDebug", "Шаг 8: Ответ получен: $response")
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


    private fun xorEncrypt(input: String, key: String): String {
        val dataBytes = input.toByteArray()
        val keyBytes = key.toByteArray()
        val result = ByteArray(dataBytes.size)
        for (i in dataBytes.indices) {
            result[i] = (dataBytes[i].toInt() xor keyBytes[i % keyBytes.size].toInt()).toByte()
        }
        return Base64.getEncoder().encodeToString(result)
    }
}
