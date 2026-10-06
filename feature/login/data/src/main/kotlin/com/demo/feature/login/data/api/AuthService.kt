package com.demo.feature.login.data.api

import com.demo.feature.login.data.model.SessionResponseDto

interface AuthService {
    suspend fun createRequestToken(): SessionResponseDto
}