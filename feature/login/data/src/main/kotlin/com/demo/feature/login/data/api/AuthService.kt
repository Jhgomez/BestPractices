package com.demo.feature.login.data.api

import com.demo.core.data.client.api.DataResult
import com.demo.feature.login.data.model.CreateSessionResponseDto
import com.demo.feature.login.data.model.RequestTokenResponseDto

internal interface AuthService {
    suspend fun createRequestToken(): DataResult<RequestTokenResponseDto>

    suspend fun createSession(token: String): DataResult<CreateSessionResponseDto>
}