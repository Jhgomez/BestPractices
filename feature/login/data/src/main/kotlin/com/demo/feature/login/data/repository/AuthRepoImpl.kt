package com.demo.feature.login.data.repository

import com.demo.feature.login.data.api.AuthService
import com.demo.feature.login.domain.AuthRepo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepoImpl @Inject constructor(private val authService: AuthService): AuthRepo {

    override suspend fun createRequestToken(): Boolean {
        authService.createRequestToken()
        return true
    }

    override suspend fun createGuestSession(): Boolean {
        TODO("Not yet implemented")
    }
}