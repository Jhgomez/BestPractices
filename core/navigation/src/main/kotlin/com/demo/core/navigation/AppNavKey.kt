package com.demo.core.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable

interface AppNavKey {
    val shouldShowTopBar: Boolean
        get() = true

    val topBarTitle: @Composable (() -> Unit)
        get() = {}

    val topBarDefaults: TopAppBarColors?
        get() = null

    @OptIn(ExperimentalMaterial3Api::class)
    val scrollBehavior: TopAppBarScrollBehavior?
        get() = null

    val topBarActions: @Composable (RowScope.() -> Unit)
        get() = {}

    val topBarExpandedHeight: Int
        get() = 80
}