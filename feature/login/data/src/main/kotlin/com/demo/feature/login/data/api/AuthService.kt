package com.demo.feature.login.data.api

import com.demo.core.data.client.api.DataResult
import com.demo.core.domain.api.model.DomainResult
import com.demo.feature.login.data.model.CreateSessionResponseDto
import com.demo.feature.login.data.model.GuestSessionResponseDto
import com.demo.feature.login.data.model.RequestTokenResponseDto
import com.demo.feature.login.domain.model.GuestSession

internal interface AuthService {
    suspend fun createRequestToken(): DataResult<RequestTokenResponseDto>

    suspend fun createSession(token: String): DataResult<CreateSessionResponseDto>
    suspend fun createGuestSession(): DataResult<GuestSessionResponseDto>
}