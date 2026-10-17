package com.demo.feature.login.data.api

import com.demo.core.data.client.api.DataResult
import com.demo.core.data.client.api.get
import com.demo.core.data.client.api.post
import com.demo.feature.login.data.model.CreateSessionBodyDto
import com.demo.feature.login.data.model.CreateSessionResponseDto
import com.demo.feature.login.data.model.GuestSessionResponseDto
import com.demo.feature.login.data.model.RequestTokenResponseDto
import okhttp3.OkHttpClient
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class AuthServiceImpl @Inject constructor(private val okHttpClient: OkHttpClient): AuthService {

    override suspend fun createRequestToken(): DataResult<RequestTokenResponseDto> =
        okHttpClient.get(
            path = "3/authentication/token/new",
            serializer = RequestTokenResponseDto.serializer()
        )

    override suspend fun createSession(token: String): DataResult<CreateSessionResponseDto> =
        okHttpClient.post(
            path = "3/authentication/session/new",
            bodyObject = CreateSessionBodyDto(token),
            bodySerializer = CreateSessionBodyDto.serializer(),
            responseSerializer = CreateSessionResponseDto.serializer(),
        )

    override suspend fun createGuestSession(): DataResult<GuestSessionResponseDto> =
        okHttpClient.get(
            path = "3/authentication/guest_session/new",
            serializer = GuestSessionResponseDto.serializer()
        )
}