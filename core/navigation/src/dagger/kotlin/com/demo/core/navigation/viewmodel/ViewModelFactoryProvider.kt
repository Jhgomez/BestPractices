package com.demo.core.navigation.viewmodel

import androidx.compose.runtime.compositionLocalOf
import androidx.lifecycle.ViewModelProvider

val LocalAppViewModelFactory = compositionLocalOf<ViewModelProvider.Factory> { throw IllegalStateException("") }