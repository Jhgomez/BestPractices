package com.demo.feature.login.data.api

import com.demo.feature.login.data.model.SessionResponseDto
import com.demo.feature.login.data.model.TokenResponseDto

interface AuthService {
    suspend fun createRequestToken(): TokenResponseDto

    suspend fun createGuestSession(): SessionResponseDto
}