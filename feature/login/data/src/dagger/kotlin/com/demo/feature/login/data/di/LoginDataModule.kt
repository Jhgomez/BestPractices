package com.demo.feature.login.data.di

import com.demo.feature.login.data.api.AuthService
import com.demo.feature.login.data.api.AuthServiceImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
internal interface LoginDataModuleImpl: LoginDataModule {

    @Binds
    @Singleton
    override fun bindsService(service: AuthServiceImpl): AuthService
}

@Module(includes = [LoginDataModuleImpl::class])
class PublicLoginDataModule