package com.nikol.data.local.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

val Context.tokenDataStore: DataStore<Preferences> by preferencesDataStore(name = "token_prefs")

class TokenStorage(context: Context) {
    private val dataStore = context.tokenDataStore
    private val json = Json { ignoreUnknownKeys = true }

    companion object {
        private val KEY_ACCESS_TOKEN = stringPreferencesKey("access_token")
    }

    suspend fun saveToken(token: String) {
        dataStore.edit { preferences ->
            preferences[KEY_ACCESS_TOKEN] = token
        }
    }

    suspend fun getToken(): String? {
        return dataStore.data
            .map { preferences -> preferences[KEY_ACCESS_TOKEN] }
            .first()
    }

    suspend fun clearToken() {
        dataStore.edit { preferences ->
            preferences.remove(KEY_ACCESS_TOKEN)
        }
    }
}