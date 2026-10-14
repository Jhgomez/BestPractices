package com.demo.feature.login.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CreateSessionBodyDto(
    val request_token: String
)
