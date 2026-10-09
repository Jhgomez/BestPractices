package com.demo.feature.login.data.model

import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Serializable
data class RequestTokenResponseDto(
    val success: Boolean,
    val request_token: String,
    @Serializable(TMDBInstantSerializer::class)
    val expires_at: Instant
)
