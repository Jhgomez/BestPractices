package com.demo.core.navigation.viewmodel

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.ViewModelProvider

val LocalAppViewModelFactory = staticCompositionLocalOf<ViewModelProvider.Factory> { throw IllegalStateException("") }