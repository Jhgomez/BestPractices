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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.defaultPredictivePopTransitionSpec
import com.demo.core.navigation.AppNavKey
import com.demo.feature.home.presentation.addHomeTopLevel
import com.demo.feature.home.presentation.api.navigation.HomeTopLevel
import com.demo.ui.navigation.AppNavigator
import com.demo.ui.navigation.TopLevelDestination
import com.demo.ui.navigation.rememberAppNavState

@Composable
fun DemoAppScreen(
    modifier: Modifier
) {
    val navState = rememberAppNavState(
        homeKey = HomeTopLevel,
        topLevelKeys = arrayOf(HomeTopLevel)
    )

    val navigator = AppNavigator(navState)

    // This composable saves us the need to configure a screen strategy for showing navigation UI
    // but only the nav bottom bar and nav rail, a top bar is not included, and since I want a top
    // bar in some screens I will have to create one but it will be a single top bar for the whole app
    NavigationSuiteScaffold(
        navigationSuiteItems = {
            TopLevelDestination.entries.forEach { destination ->
                item(
                    selected = destination.navKey === navState.selectedTopLevelKey.value,
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
        modifier = modifier
    ) {
        SharedTransitionLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val provider = entryProvider {
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