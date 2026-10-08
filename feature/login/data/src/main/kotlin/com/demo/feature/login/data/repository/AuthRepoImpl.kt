package com.demo.feature.login.data.repository

import com.demo.core.domain.api.model.DomainResult
import com.demo.feature.login.data.api.AuthService
import com.demo.feature.login.domain.AuthRepo
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AuthRepoImpl @Inject constructor(private val authService: AuthService): AuthRepo {

    override suspend fun createRequestToken(): DomainResult<Unit> {
//        authService.createRequestToken()
        TODO("Not yet implemented")
    }

    override suspend fun createGuestSession(): DomainResult<Unit> {
        TODO("Not yet implemented")
    }
}