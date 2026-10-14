package com.demo.feature.login.presentation

import android.util.Log
import androidx.activity.compose.LocalActivity
import androidx.activity.result.ActivityResultCaller
import androidx.browser.auth.AuthTabIntent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import com.demo.core.presentation.LocalAppViewModelFactory
import com.demo.feature.login.presentation.LoginEvent.*

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier
) {
    val vm = viewModel<LoginViewModel>(factory = LocalAppViewModelFactory.current)
    val activity: ActivityResultCaller = LocalActivity.current as? ActivityResultCaller
        ?: throw IllegalStateException("composable should live inside an activity implementing ActivityResultCaller")


    LaunchedEffect(null) {
        vm.events.collect { event ->
            when (event) {
                is NavigateToHome -> {
                    Log.d("User Auth", "Navigate to home triggered")
                }

                is RedirectToAuthorizeToken -> {
                    val appScheme = "bpapp"

                    val url =
                        "https://www.themoviedb.org/authenticate/${event.token}?redirect_to=$appScheme://callback"

                    val customTabIntent = AuthTabIntent
                        .Builder()
                        .build()

                    customTabIntent.launch(mLauncher, url.toUri(), appScheme)
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
