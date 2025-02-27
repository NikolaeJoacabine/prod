package com.nikol.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class EncryptedCredentials(
    val encryptedLogin: String,
    val encryptedPassword: String
)
