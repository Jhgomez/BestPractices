package com.demo.feature.login.domain

import com.demo.core.domain.api.model.DomainResult
import com.demo.feature.login.domain.model.GuestSession
import com.demo.feature.login.domain.model.RequestToken
import kotlinx.coroutines.flow.Flow

interface AuthRepo {

    suspend fun createRequestToken(): DomainResult<RequestToken>
    suspend fun createSession(): DomainResult<Unit>

    suspend fun createGuestSession(): DomainResult<GuestSession>

    fun authenticationFlow(): Flow<Boolean>
}