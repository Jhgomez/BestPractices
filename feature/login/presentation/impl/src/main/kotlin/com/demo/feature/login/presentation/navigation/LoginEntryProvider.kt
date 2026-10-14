package com.demo.feature.login.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.EntryProviderScope
import com.demo.core.presentation.navigation.AppNavKey
import com.demo.feature.login.presentation.LoginScreen
import com.demo.feature.login.presentation.api.navigation.LoginScreenKey

fun EntryProviderScope<AppNavKey>.addLogin() {
    entry<LoginScreenKey> {
        LoginScreen(modifier = Modifier.fillMaxSize())
    }
}