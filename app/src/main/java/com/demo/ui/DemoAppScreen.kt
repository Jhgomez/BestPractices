package com.demo.ui

import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteItem
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.intl.LocaleList
import com.demo.ui.navigation.TopLevelKeys

@Composable
fun DemoAppScreen(
    modifier: Modifier,
) {
    // This composable saves us the need to configure a screen strategy for showing navigation UI
    // but only the nav bottom bar and nav rail, a top bar is not included, and since I want a top
    // bar in some screens I will have to create one but it will be a single top bar for the whole app
    NavigationSuiteScaffold(
        navigationSuiteItems = {
            TopLevelKeys.entries.forEach {
                item(
                    selected = false,
                    onClick = {

                    },
                    icon = {},
                    modifier = Modifier,
                    enabled = true,
                    label = {},
                    alwaysShowLabel = true,
                    badge = {},
                    colors = null,
                    interactionSource = null
                )
            }
        },
        modifier = Modifier
    ) {

    }
}