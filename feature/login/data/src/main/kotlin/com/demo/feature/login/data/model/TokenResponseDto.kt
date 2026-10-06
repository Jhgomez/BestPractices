package com.demo.feature.login.data.model

import android.util.Log
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@Serializable
data class TokenResponseDto(
    val success: Boolean,
    val request_token: String,
    @Serializable(TMDBInstantSerializer::class)
    val expires_at: Instant
)
