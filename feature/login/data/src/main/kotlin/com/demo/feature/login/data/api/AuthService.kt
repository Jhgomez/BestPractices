package com.demo.feature.login.data.api

import com.demo.core.data.client.api.DataResult
import com.demo.feature.login.data.model.SessionResponseDto
import com.demo.feature.login.data.model.TokenResponseDto

interface AuthService {
    suspend fun createRequestToken(): DataResult<TokenResponseDto>

    suspend fun createGuestSession(): DataResult<SessionResponseDto>
}