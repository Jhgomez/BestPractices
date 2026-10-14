package com.demo.feature.login.presentation.api.navigation

import com.demo.core.navigation.AppNavKey
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
object LoginScreenKey : AppNavKey {

    @IgnoredOnParcel
    override val shouldShowAppNavigationUi: Boolean = false
}