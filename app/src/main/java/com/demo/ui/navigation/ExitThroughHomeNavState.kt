package com.demo.ui.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
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
import com.demo.core.navigation.AppNavKey

data class NestedNav(
    val topLevelKey: AppNavKey,
    val nestedStack: SnapshotStateList<AppNavKey>
)

@Composable
fun rememberAppNavState(
    homeKey: AppNavKey,
    topLevelKeys: Array<AppNavKey>,
): ExitThroughHomeNavState {
    val currentTopLevelKeys = rememberSaveable {  mutableStateListOf(homeKey) }
    val nestedNavStacks = Array(topLevelKeys.size) { index ->
        NestedNav(
            topLevelKey = topLevelKeys[index],
            nestedStack = rememberSaveable { mutableStateListOf(topLevelKeys[index]) }
        )
    }

    return remember {
        ExitThroughHomeNavState(
            nestedNavStacks = nestedNavStacks,
            currentTopLevelKeys = currentTopLevelKeys
        )
    }
}

/**
 * @param nestedNavStacks Contains each top level's nested navigation back stack, I use a combination
 *  of a Custom object and an array to avoid instantiating a Map just for a simple task, its elements
 *  order is not important, changes in the nested nav stack modifies the back stack Nav display
 *  receives due to the logic in function [decorateAndRememberNavEntries]
 * @param currentTopLevelKeys Used to keep track of what top level key is on top, notice that
 *  home key should(will) always be at the bottom of the stack, thereby the class name. Changes in
 *  this list modifies the back stack Nav display receives due to the logic in
 *  function [decorateAndRememberNavEntries]
 * @param currentStack Used for merging all the nested nav stack, they are all decorated and
 *  sent to NavDisplay, this is the actual screens the user navigates on pressing back
 */
class ExitThroughHomeNavState @OptIn(ExperimentalMaterial3Api::class) constructor(
    val nestedNavStacks: Array<NestedNav>,
    val currentTopLevelKeys: SnapshotStateList<AppNavKey>
) {
    val selectedTopLevelKey = derivedStateOf { currentTopLevelKeys.last() }

    @Composable
    fun decorateAndRememberNavEntries(
        provider: (AppNavKey) -> NavEntry<AppNavKey>
    ): SnapshotStateList<NavEntry<AppNavKey>> {
        val decoratedNestedNav = nestedNavStacks.map { nestedStack ->
            val decorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator<AppNavKey>(),
                rememberViewModelStoreNavEntryDecorator<AppNavKey>(),
            )

            nestedStack.topLevelKey to rememberDecoratedNavEntries(
                backStack = nestedStack.nestedStack,
                entryDecorators = decorators,
                entryProvider = provider
            )
        }

        return currentTopLevelKeys.flatMap { key ->
            decoratedNestedNav.find { (topLevelKey, _) ->
                topLevelKey == key
            }?.second ?: throw IllegalStateException("Nav state configured incorrectly")
        }.toMutableStateList()
    }
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