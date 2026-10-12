package com.demo.feature.login.presentation.di

import androidx.lifecycle.ViewModel
import com.demo.di.ViewMapModelKey
import com.demo.feature.login.presentation.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface LoginPresentationModuleImpl: LoginPresentationModule {

    @Binds
    @IntoMap
    @ViewMapModelKey(value = LoginViewModel::class)
    override fun bindsLoginViewmodel(loginViewModel: LoginViewModel): ViewModel
}

@Module(includes = [LoginPresentationModuleImpl::class])
interface  PublicLoginPresentationModule
