package com.nikol.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EncryptionKeyResponse(
    @SerialName("encryption_key") val key: String
)
