package com.demo.feature.login.data.model

import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Serializable
data class SessionResponseDto(
    val success: Boolean,
    val session_id: String
)
