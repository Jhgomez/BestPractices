package com.demo.feature.login.domain.model

data class GuestSession(
    val id: String,
    val expiresAt: Long // epoch milli seconds
)
