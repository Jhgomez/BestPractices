package com.demo.feature.login.domain.model

data class DomainGuestSession(
    val id: String,
    val expiresAt: Long // epoch milli seconds
)
