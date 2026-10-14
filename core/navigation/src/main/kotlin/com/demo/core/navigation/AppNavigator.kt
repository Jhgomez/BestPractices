package com.demo.core.navigation

class AppNavigator(private val state: ExitThroughHomeNavState) {

    fun navigate(navKey: AppNavKey, popupTo: AppNavKey, inclusive: Boolean) {
//        val currentTopLevelNestedNav = state.nestedNavStacks.find { (topLevelKey, _) ->
//            topLevelKey == state.currentNestedNav.topLevelKey
//        } ?: throw IllegalStateException("Nav state configured incorrectly")
//
//        val topLevelStack = state.nestedNavStacks
//            .find { (topLevelKey, _) -> topLevelKey == navKey }
//
//        val isTopLevel = topLevelStack != null
//
//        if (isTopLevel) {
//            topLevelStack.nestedStack.clear()
//        }
    }

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

//        val currentTopLevelNestedNav = state.nestedNavStacks.find { (topLevelKey, _) ->
//            topLevelKey == state.currentNestedNav
//        } ?: throw IllegalStateException("Nav state configured incorrectly")

        state.currentNestedNav.nestedStack.add(navKey)
    }

    fun onBack() {
//        val currentTopLevelNestedNav = state.nestedNavStacks.find { (topLevelKey, _) ->
//            topLevelKey == state.currentNestedNav
//        } ?: throw IllegalStateException("Nav state configured incorrectly")

        // means we need to remove a top level nested nav stack but we should not remove home key
        if ( state.currentNestedNav.nestedStack.size == 1 && state.currentTopLevelKeys.size > 1) {
            state.currentTopLevelKeys.removeAt(state.currentTopLevelKeys.size)

            return
        }

        state.currentNestedNav.nestedStack.removeAt(
            state.currentNestedNav.nestedStack.size - 1
        )
    }
}