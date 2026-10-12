package com.demo.ui.navigation

import androidx.navigation3.runtime.NavKey
import com.demo.core.navigation.AppNavKey

class AppNavigator(private val state: ExitThroughHomeNavState) {

    fun navigate(navKey: AppNavKey) {
        val isTopLevel = state.nestedNavStacks
            .find { (topLevelKey, _) -> topLevelKey == navKey } != null

        if (isTopLevel) {
            val isHomeKey = navKey == state.currentTopLevelKeys.first()

            // only leave home key stack
            if (isHomeKey) {
                state.currentTopLevelKeys.removeRange(1, state.currentTopLevelKeys.size)
            } else {
                state.currentTopLevelKeys.remove(navKey)
                state.currentTopLevelKeys.add(navKey)
            }

            return
        }

        val currentTopLevelNestedNav = state.nestedNavStacks.find { (topLevelKey, _) ->
            topLevelKey == state.selectedTopLevelKey
        } ?: throw IllegalStateException("Nav state configured incorrectly")

        currentTopLevelNestedNav.nestedStack.add(navKey)
    }

    fun onBack() {
        val currentTopLevelNestedNav = state.nestedNavStacks.find { (topLevelKey, _) ->
            topLevelKey == state.selectedTopLevelKey
        } ?: throw IllegalStateException("Nav state configured incorrectly")

        // means we need to remove a top level nested nav stack but we should not remove home key
        if (currentTopLevelNestedNav.nestedStack.size == 1 && state.currentTopLevelKeys.size > 1) {
            state.currentTopLevelKeys.removeAt(state.currentTopLevelKeys.size)

            return
        }

        currentTopLevelNestedNav.nestedStack.removeAt(
            currentTopLevelNestedNav.nestedStack.size
        )
    }
}