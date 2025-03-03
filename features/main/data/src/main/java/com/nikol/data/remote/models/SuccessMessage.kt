package com.nikol.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SuccessMessage(
    @SerialName("message")
    val image: String
)
