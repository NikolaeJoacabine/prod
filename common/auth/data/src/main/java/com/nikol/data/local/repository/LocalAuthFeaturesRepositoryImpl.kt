package com.nikol.data.local.repository

import com.nikol.data.local.models.UserPreferences
import com.nikol.data.local.storage.UserStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalAuthFeaturesRepositoryImpl(
    private val userStorage: UserStorage
) : LocalAuthFeaturesRepository {

    override suspend fun saveCredentials(email: String, password: String) {
        withContext(Dispatchers.IO) {
            userStorage.saveUser(UserPreferences(email, password))
        }
    }

    override suspend fun getCredentials(): Pair<String, String>? {
        return withContext(Dispatchers.IO) {
            val user = userStorage.getUser()
            user?.let { Pair(it.email, it.password) }
        }
    }

    override suspend fun clearCredentials() {
        withContext(Dispatchers.IO) {
            userStorage.clearUser()
        }
    }
}