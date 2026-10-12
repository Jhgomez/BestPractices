package com.demo.feature.login.data.di

import com.demo.feature.login.data.api.AuthService
import com.demo.feature.login.data.api.AuthServiceImpl
import com.demo.feature.login.data.repository.AuthRepoImpl
import com.demo.feature.login.domain.AuthRepo

internal interface LoginDataModule {
    fun bindsService(service: AuthServiceImpl): AuthService
    fun bindsRepository(repo: AuthRepoImpl): AuthRepo
}