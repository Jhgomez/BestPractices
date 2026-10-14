package com.demo.feature.login.presentation

import android.util.Log
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import com.demo.core.navigation.viewmodel.AppViewModelFactory
import com.demo.feature.login.presentation.LoginEvent.*

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier
) {
    val vm = viewModel<LoginViewModel>(factory = AppViewModelFactory.current)
    val context = LocalContext.current

    LaunchedEffect(null) {
        vm.events.collect { event ->
            when (event) {
                is NavigateToHome -> {
                    Log.d("User Auth", "Navigate to home triggered")
                }

                is RedirectToAuthorizeToken -> {

                    val url = "https://www.themoviedb.org/authenticate/${event.token}"

                    val customTabIntent = CustomTabsIntent
                        .Builder()
                        .build()

                    customTabIntent.launchUrl(context, url.toUri())
                }
            }
        }
    }

    LoginScreen(
        modifier = modifier,
        isUsernameWrong = vm.state.value.isUsernameWrong,
        isPasswordWrong = vm.state.value.isPasswordWrong,
        handleIntent = { intent ->
            vm.handleIntent(intent)
        },
    )
}