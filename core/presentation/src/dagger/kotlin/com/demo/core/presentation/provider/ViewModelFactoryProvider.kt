package com.demo.core.presentation

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.ViewModelProvider

val LocalAppViewModelFactory =
    staticCompositionLocalOf<ViewModelProvider.Factory> {
        throw IllegalStateException("Pass value to LocalAppViewModelFactory before consuming it")
    }