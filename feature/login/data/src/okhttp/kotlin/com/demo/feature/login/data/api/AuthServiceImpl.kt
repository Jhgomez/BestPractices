package com.demo.feature.login.data.api

import com.demo.core.data.client.common.get
import com.demo.feature.login.data.model.SessionResponseDto
import okhttp3.OkHttpClient
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthServiceImpl @Inject constructor(private val okHttpClient: OkHttpClient): AuthService {

    override suspend fun createRequestToken(): SessionResponseDto =
        okHttpClient.get(
            path = "authentication/token/new",
            serializer = SessionResponseDto.serializer()
        )
}