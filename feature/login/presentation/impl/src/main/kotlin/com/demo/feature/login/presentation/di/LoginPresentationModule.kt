package com.demo.feature.login.presentation.di

import android.view.View
import androidx.lifecycle.ViewModel
import com.demo.feature.login.presentation.LoginViewModel

internal interface LoginPresentationModule {
    fun bindsLoginViewmodel(loginViewModel: LoginViewModel): ViewModel
}