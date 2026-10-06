package com.demo.feature.login.data.di

import com.demo.feature.login.data.api.AuthService
import com.demo.feature.login.data.api.AuthServiceImpl

internal interface LoginDataModule {
    fun bindsService(service: AuthServiceImpl): AuthService
}