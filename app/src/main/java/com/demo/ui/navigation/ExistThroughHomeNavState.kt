package com.demo.ui.navigation

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

data class NestedStack(
    val topLevelKey: NavKey,
    val nestedStack: NavBackStack<NavKey>
)

class ExistThroughHomeNavState @OptIn(ExperimentalMaterial3Api::class) constructor(
    val topLevelKeys: Array<NavKey>,
    val currentStack: NavBackStack<NavKey>,
    val nestedStacks: Array<NestedStack>
)

class AppBarState @OptIn(ExperimentalMaterial3Api::class) constructor(
    shouldShowTopBar: Boolean = true,
    topBarTitle: @Composable () -> Unit = {},
    topBarDefaults: TopAppBarColors,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    topBarActions: @Composable RowScope.() -> Unit = {},
    topBarExpandedHeight: Int = 80
) {
    var shouldShowTopBar by mutableStateOf(shouldShowTopBar)
        private set

    var topBarTitle by mutableStateOf(topBarTitle)
        private set

    var topBarDefaults by mutableStateOf(topBarDefaults)
        private set

    @OptIn(ExperimentalMaterial3Api::class)
    var scrollBehavior by mutableStateOf(scrollBehavior)
        private set

    var topBarActions by mutableStateOf(topBarActions)
        private set

    var topBarExpandedHeight by mutableStateOf(topBarExpandedHeight)
        private set



    fun setShouldShowTopBar(shouldShowTopBar: Boolean) {
        this.shouldShowTopBar = shouldShowTopBar
    }

    fun setTopBarTitle(topBarTitle: @Composable () -> Unit) {
        this.topBarTitle = topBarTitle
    }

    fun setTopBarDefaults(topBarDefaults: TopAppBarColors) {
        this.topBarDefaults = topBarDefaults
    }

    @OptIn(ExperimentalMaterial3Api::class)
    fun setScrollBehavior(scrollBehavior: TopAppBarScrollBehavior?) {
        this.scrollBehavior = scrollBehavior
    }

    fun setTopBarActions(topBarActions: @Composable RowScope.() -> Unit) {
        this.topBarActions = topBarActions
    }

    fun setTopBarExpandedHeight(topBarExpandedHeight: Int) {
        this.topBarExpandedHeight = topBarExpandedHeight
    }
}