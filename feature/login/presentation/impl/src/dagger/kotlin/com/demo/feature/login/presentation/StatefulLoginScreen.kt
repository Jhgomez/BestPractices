package com.demo.feature.login.presentation

import android.content.ComponentName
import android.net.Uri
import android.util.Log
import androidx.activity.compose.LocalActivity
import androidx.activity.result.ActivityResultCaller
import androidx.browser.auth.AuthTabIntent
import androidx.browser.customtabs.CustomTabsClient
import androidx.browser.customtabs.CustomTabsServiceConnection
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import com.demo.core.presentation.LocalAppViewModelFactory
import com.demo.core.presentation.LocalLoginActivityResultLauncher
import com.demo.feature.login.presentation.LoginEvent.*
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier
) {
    val vm = viewModel<LoginViewModel>(factory = LocalAppViewModelFactory.current)
    val mLauncher = LocalLoginActivityResultLauncher.current

    val context = LocalContext.current

    LaunchedEffect(null) {
        launch {
            vm.authenticationFlow.collect {
                Log.d("User Auth", "login screen auth ok")
            }
        }

        vm.events.collect { event ->
            when (event) {
                is NavigateToHome -> {
                    Log.d("User Auth", "Navigate to home triggered")
                }

                is RedirectToAuthorizeToken -> {

                    val redirectScheme = "tmdbauth"           // your own scheme, lowercase, no underscores
                    val redirectUri = "$redirectScheme://callback"

                    val url =
                        "https://www.themoviedb.org/authenticate/${event.token}?redirect_to=${Uri.encode(redirectUri)}"

                    val customTabIntent = AuthTabIntent
                        .Builder()
                        .build()

                    customTabIntent.launch(mLauncher, url.toUri(), redirectScheme)
                }

                is ShowSnackBar -> {

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
