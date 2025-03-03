package com.nikol.data.local.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.nikol.data.local.models.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

val Context.userDataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")
class UserStorage(context: Context) {
    private val dataStore = context.userDataStore
    private val json = Json { ignoreUnknownKeys = true }

    companion object {
        private val KEY_USER = stringPreferencesKey("user")
    }
    suspend fun saveUser(user: UserPreferences) {
        val userJson = json.encodeToString(user)
        dataStore.edit { preferences ->
            preferences[KEY_USER] = userJson
        }
    }

    fun getUserFlow(): Flow<UserPreferences?> {
        return dataStore.data
            .map { preferences ->
                preferences[KEY_USER]?.let { userJson ->
                    try {
                        json.decodeFromString<UserPreferences>(userJson)
                    } catch (e: Exception) {
                        null
                    }
                }
            }
    }

    suspend fun clearUser() {
        dataStore.edit { preferences ->
            preferences.remove(KEY_USER)
        }
    }
}