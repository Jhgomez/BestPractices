package com.demo.feature.login.domain

import com.demo.core.domain.api.model.DomainResult


interface AuthRepo {

    suspend fun createRequestToken(): DomainResult<Unit>

    suspend fun createGuestSession(): DomainResult<Unit>
}