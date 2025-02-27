package com.nikol.data.local.storage

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.nikol.data.local.models.UserPreferences
import kotlinx.serialization.json.Json

class UserStorage(context: Context) {
    private val sharedPreferences = createEncryptedSharedPreferences(context)
    private val json = Json { ignoreUnknownKeys = true }

    companion object {
        private const val PREFS_NAME = "user_prefs"
        private const val KEY_USER = "user"
    }

    private fun createEncryptedSharedPreferences(context: Context) =
        EncryptedSharedPreferences.create(
            context,
            PREFS_NAME,
            MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    fun saveUser(user: UserPreferences) {
        val userJson = json.encodeToString(UserPreferences.serializer(), user)
        with(sharedPreferences.edit()) {
            putString(KEY_USER, userJson)
            apply()
        }
    }

    fun getUser(): UserPreferences? {
        val userJson = sharedPreferences.getString(KEY_USER, null) ?: return null
        return try {
            json.decodeFromString(UserPreferences.serializer(), userJson)
        } catch (e: Exception) {
            null
        }
    }

    fun clearUser() {
        with(sharedPreferences.edit()) {
            remove(KEY_USER)
            apply()
        }
    }
}