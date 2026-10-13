package com.demo.ui

import androidx.compose.animation.ContentTransform
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldValue
import androidx.compose.material3.adaptive.navigationsuite.rememberNavigationSuiteScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.defaultPredictivePopTransitionSpec
import com.demo.feature.home.presentation.navigation.addHomeTopLevel
import com.demo.feature.login.presentation.api.navigation.LoginScreenKey
import com.demo.feature.login.presentation.navigation.addLogin
import com.demo.core.navigation.AppNavigator
import com.demo.ui.navigation.TopLevelDestination
import com.demo.core.navigation.rememberAppNavState

@Composable
fun DemoAppScreen(
    modifier: Modifier
) {
    val navState = rememberAppNavState(
        homeKey = LoginScreenKey,
        topLevelKeys = arrayOf(LoginScreenKey)
    )

    val navigator = AppNavigator(navState)

    val navigationSuiteScaffoldState = rememberNavigationSuiteScaffoldState()

    LaunchedEffect(
        navState.currentNestedNav.nestedStack.last().shouldShowAppNavigationUi
    ) {
        navigationSuiteScaffoldState.snapTo(
            if (navState.currentNestedNav.nestedStack.last().shouldShowAppNavigationUi)
                NavigationSuiteScaffoldValue.Visible
            else
                NavigationSuiteScaffoldValue.Hidden
        )
    }


    // This composable saves us the need to configure a screen strategy for showing navigation UI
    // but only the nav bottom bar and nav rail, a top bar is not included, and since I want a top
    // bar in some screens I will have to create one but it will be a single top bar for the whole app
    NavigationSuiteScaffold(
        navigationSuiteItems = {
            TopLevelDestination.entries.forEach { destination ->
                item(
                    selected = destination.navKey === navState.currentNestedNav.topLevelKey,
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
                        // this is used to display things like unread messages
                    },
                    colors = null,
                    interactionSource = null
                )
            }
        },
        state = navigationSuiteScaffoldState,
        modifier = modifier
    ) {
        SharedTransitionLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val provider = entryProvider {
                addLogin()
                addHomeTopLevel()
            }

            NavDisplay(
                entries = navState.decorateAndRememberNavEntries(provider),
                modifier = Modifier.fillMaxSize(),
//            sceneStrategies = ,
//            sceneDecoratorStrategies = ,
                sharedTransitionScope = this@SharedTransitionLayout,
                sizeTransform = SizeTransform { initialSize, targetSize ->
                    snap(2000)
                },
                transitionSpec = {
                    ContentTransform(
                        fadeIn(animationSpec = tween(700)),
                        fadeOut(animationSpec = tween(700)),
                    )
                },
                popTransitionSpec = {
                    ContentTransform(
                        fadeIn(animationSpec = tween(700)),
                        fadeOut(animationSpec = tween(700)),
                    )
                },
                onBack = navigator::onBack,
                predictivePopTransitionSpec = defaultPredictivePopTransitionSpec(),
            )
        }
    }
}