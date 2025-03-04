package com.nikol.data.local.repository

import com.nikol.data.local.models.UserPreferences
import com.nikol.data.local.storage.TokenStorage
import com.nikol.data.local.storage.UserStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class LocalAuthFeaturesRepositoryImpl(
    private val userStorage: UserStorage
) : LocalAuthFeaturesRepository {

    override suspend fun saveCredentials(email: String, password: String) {
        userStorage.saveUser(UserPreferences(email, password))
    }

    override suspend fun getCredentials(): Flow<Pair<String, String>?> {
        return userStorage.getUserFlow()
            .map { user ->
                user?.let { Pair(it.email, it.password) }
            }
    }

    override suspend fun clearCredentials() {
        userStorage.clearUser()
    }
}