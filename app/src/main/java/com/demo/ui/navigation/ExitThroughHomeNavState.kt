package com.demo.ui.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberDecoratedNavEntries
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator

data class NestedNav(
    val topLevelKey: NavKey,
    val nestedStack: SnapshotStateList<NavKey>
)

@Composable
fun rememberAppNavState(
    homeKey: NavKey,
    topLevelKeys: Array<NavKey>,
): ExitThroughHomeNavState {
    val currentTopLevel = rememberSaveable {  mutableStateOf(homeKey) }
    val currentStack = rememberSaveable { mutableStateListOf(homeKey) }
    val nestedNavStacks = Array(topLevelKeys.size) { index ->
        NestedNav(
            topLevelKey = topLevelKeys[index],
            nestedStack = rememberSaveable { mutableStateListOf(topLevelKeys[index]) }
        )
    }

    return remember {
        ExitThroughHomeNavState(
            homeKey = homeKey,
            currentTopLevel = currentTopLevel,
            topLevelKeys = topLevelKeys,
            currentStack = currentStack,
            nestedNavStacks = nestedNavStacks
        )
    }
}

class ExitThroughHomeNavState @OptIn(ExperimentalMaterial3Api::class) constructor(
    val homeKey: NavKey,
    val currentTopLevel: MutableState<NavKey>,
    val topLevelKeys: Array<NavKey>,
    val currentStack: SnapshotStateList<NavKey>,
    val nestedNavStacks: Array<NestedNav>
) {

    @Composable
    fun decorateAndRememberNavEntries(
        provider: (NavKey) -> NavEntry<NavKey>
    ): SnapshotStateList<NavEntry<NavKey>> = nestedNavStacks.flatMap { nestedStack ->
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