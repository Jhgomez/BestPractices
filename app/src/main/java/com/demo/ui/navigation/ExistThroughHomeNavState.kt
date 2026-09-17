package com.demo.ui.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberDecoratedNavEntries
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator

data class NestedStack(
    val topLevelKey: NavKey,
    val nestedStack: NavBackStack<NavKey>
)

class ExistThroughHomeNavState @OptIn(ExperimentalMaterial3Api::class) constructor(
    val homeKey: NavKey,
    val topLevelKeys: Array<NavKey>,
    val currentStack: NavBackStack<NavKey>,
    val nestedStacks: Array<NestedStack>
) {
    @Composable
    fun decorateAndRememberNavEntries(
        provider: (NavKey) -> NavEntry<NavKey>
    ): SnapshotStateList<NavEntry<NavKey>> = nestedStacks.flatMap { nestedStack ->
            val decorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator<NavKey>(),
                rememberViewModelStoreNavEntryDecorator<NavKey>(),
            )

            rememberDecoratedNavEntries(
                backStack = nestedStack.nestedStack,
                entryDecorators = decorators,
                entryProvider = provider
            )
        }.toMutableStateList()
}

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



    fun updateShouldShowTopBar(shouldShowTopBar: Boolean) {
        this.shouldShowTopBar = shouldShowTopBar
    }

    fun updateTopBarTitle(topBarTitle: @Composable () -> Unit) {
        this.topBarTitle = topBarTitle
    }

    fun updateTopBarDefaults(topBarDefaults: TopAppBarColors) {
        this.topBarDefaults = topBarDefaults
    }

    @OptIn(ExperimentalMaterial3Api::class)
    fun updateScrollBehavior(scrollBehavior: TopAppBarScrollBehavior?) {
        this.scrollBehavior = scrollBehavior
    }

    fun updateTopBarActions(topBarActions: @Composable RowScope.() -> Unit) {
        this.topBarActions = topBarActions
    }

    fun updateTopBarExpandedHeight(topBarExpandedHeight: Int) {
        this.topBarExpandedHeight = topBarExpandedHeight
    }
}