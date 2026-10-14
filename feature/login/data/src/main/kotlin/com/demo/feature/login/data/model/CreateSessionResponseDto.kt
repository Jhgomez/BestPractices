package com.demo.feature.login.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CreateSessionResponseDto(
    val success: Boolean,
    val session_id: String
)
