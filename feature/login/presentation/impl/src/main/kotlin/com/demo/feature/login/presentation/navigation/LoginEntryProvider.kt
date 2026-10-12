package com.demo.feature.login.presentation.navigation

import androidx.navigation3.runtime.EntryProviderScope
import com.demo.core.navigation.AppNavKey
import com.demo.feature.login.presentation.LoginScreen
import com.demo.feature.login.presentation.api.navigation.LoginScreenKey

fun EntryProviderScope<AppNavKey>.addLogin(onNavigateToHome: () -> Unit) {
    entry<LoginScreenKey> {
        LoginScreen(onNavigateToHome = onNavigateToHome)
    }
}