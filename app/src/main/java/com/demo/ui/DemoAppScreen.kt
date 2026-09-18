package com.demo.ui

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation3.runtime.NavKey
import com.demo.R
import com.demo.ui.navigation.TopLevelDestination

@Composable
fun DemoAppScreen(
    onNavigate: (NavKey) -> Unit,
    modifier: Modifier
) {
    // This composable saves us the need to configure a screen strategy for showing navigation UI
    // but only the nav bottom bar and nav rail, a top bar is not included, and since I want a top
    // bar in some screens I will have to create one but it will be a single top bar for the whole app
    NavigationSuiteScaffold(
        navigationSuiteItems = {
            TopLevelDestination.entries.forEach { destination ->
                item(
                    selected = false,
                    onClick = {

                    },
                    icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = destination.icon),
                            contentDescription = null
                        )
                    },
                    modifier = Modifier,
                    enabled = true,
                    label = {
                        Text(text = stringResource(id = destination.title))
                    },
                    alwaysShowLabel = true,
                    badge = {
                        // this is used to display thinks like unread messages
                    },
                    colors = null,
                    interactionSource = null
                )
            }
        },
        modifier = Modifier
    ) {

    }
}