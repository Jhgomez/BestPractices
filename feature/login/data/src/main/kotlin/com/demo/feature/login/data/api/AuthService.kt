package com.demo.feature.login.data.api

import com.demo.core.data.client.api.DataResult
import com.demo.feature.login.data.model.SessionResponseDto
import com.demo.feature.login.data.model.RequestTokenResponseDto

interface AuthService {
    suspend fun createRequestToken(): DataResult<RequestTokenResponseDto>

    suspend fun createSession(): DataResult<SessionResponseDto>
}