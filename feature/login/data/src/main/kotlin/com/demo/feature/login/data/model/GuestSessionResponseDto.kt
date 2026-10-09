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
data class SessionResponseDto(
    val success: Boolean,
    val guest_session_id: String,
    @Serializable(TMDBInstantSerializer::class)
    val expires_at: Instant
)
