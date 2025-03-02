package com.nikol.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class SessionDTO(
    val invitedGuestLogin: String
)