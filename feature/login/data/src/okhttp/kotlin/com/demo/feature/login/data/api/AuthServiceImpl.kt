package com.demo.feature.login.data.api

import com.demo.core.data.client.api.get
import com.demo.feature.login.data.model.SessionResponseDto
import com.demo.feature.login.data.model.TokenResponseDto
import okhttp3.OkHttpClient
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthServiceImpl @Inject constructor(private val okHttpClient: OkHttpClient): AuthService {

    override suspend fun createRequestToken(): TokenResponseDto =
        okHttpClient.get(
            path = "authentication/token/new",
            serializer = TokenResponseDto.serializer()
        )

    override suspend fun createGuestSession(): SessionResponseDto =
        okHttpClient.get(
            path = "authentication/guest_session/new",
            serializer = SessionResponseDto.serializer()
        )
}