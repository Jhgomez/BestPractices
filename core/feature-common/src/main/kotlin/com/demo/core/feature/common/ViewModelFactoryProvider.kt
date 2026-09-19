package com.demo.core.feature.common

import androidx.compose.runtime.compositionLocalOf
import androidx.lifecycle.ViewModelProvider

val AppViewModelFactory = compositionLocalOf<ViewModelProvider.Factory> { throw IllegalStateException("") }