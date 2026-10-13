package com.demo.feature.login.domain

import com.demo.core.domain.api.model.DomainResult
import com.demo.feature.login.domain.model.RequestToken


interface AuthRepo {

    suspend fun createRequestToken(): DomainResult<RequestToken>

    suspend fun createGuestSession(): DomainResult<Unit>
}