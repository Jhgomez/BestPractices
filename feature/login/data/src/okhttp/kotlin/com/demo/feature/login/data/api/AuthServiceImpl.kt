package com.demo.feature.login.data.api

import com.demo.core.data.client.api.DataResult
import com.demo.core.data.client.api.get
import com.demo.feature.login.data.model.SessionResponseDto
import com.demo.feature.login.data.model.RequestTokenResponseDto
import okhttp3.OkHttpClient
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthServiceImpl @Inject constructor(private val okHttpClient: OkHttpClient): AuthService {

    override suspend fun createRequestToken(): DataResult<RequestTokenResponseDto> =
        okHttpClient.get(
            path = "authentication/token/new",
            serializer = RequestTokenResponseDto.serializer()
        )

    override suspend fun createSession(): DataResult<SessionResponseDto> =
        okHttpClient.get(
            path = "authentication/guest_session/new",
            serializer = SessionResponseDto.serializer()
        )
}