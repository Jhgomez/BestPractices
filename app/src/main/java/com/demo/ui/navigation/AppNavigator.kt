package com.demo.ui.navigation

import androidx.navigation3.runtime.NavKey

class AppNavigator(private val state: ExitThroughHomeNavState) {

    fun navigate(navKey: NavKey) {
        if (navKey == state.homeKey) {
            state.currentStack.clear()
            // careful here, I do this because I know home key is the first index
            state.currentStack.addAll(
                state.nestedNavStacks.first().nestedStack
            )

            state.currentTopLevel.value = navKey

            return
        }

        // impossible to be null
        val nestedStack = state.nestedNavStacks.find { stack ->
            stack.topLevelKey == navKey
        }?.nestedStack!!

        // Means is top level but is not home key
        if (state.topLevelKeys.contains(navKey)) {
            state.currentStack.clear()
            state.currentStack.addAll(
                state.nestedNavStacks.first().nestedStack
            )

            state.currentStack.addAll(nestedStack)

            state.currentTopLevel.value = navKey

            return
        }

        // At this point this is a nested navigation
    }

    fun toTopLevel(navKey: NavKey) {

    }
}