package com.demo.feature.login.domain.model

data class RequestToken(
    val token: String,
    val expiresAt: Long // epoch milli seconds
)
