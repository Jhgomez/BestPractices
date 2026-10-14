package com.demo.feature.login.presentation

import android.content.Intent
import android.util.Log
import androidx.activity.compose.LocalActivity
import androidx.activity.result.ActivityResultCaller
import androidx.activity.result.ActivityResultLauncher
import androidx.browser.auth.AuthTabIntent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import com.demo.core.navigation.viewmodel.AppViewModelFactory
import com.demo.feature.login.presentation.LoginEvent.*

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier
) {
    val vm = viewModel<LoginViewModel>(factory = AppViewModelFactory.current)
    val activity: ActivityResultCaller = LocalActivity.current as? ActivityResultCaller
        ?: throw IllegalStateException("composable should live inside an activity implementing ActivityResultCaller")

    val mLauncher: ActivityResultLauncher<Intent> =
        AuthTabIntent.registerActivityResultLauncher(
            activity,
            { result ->
                handleAuthResult(
                    result = result,
                    onSuccess = {
                        vm.onTokenAuthorization()
                    },
                    onFail = { message ->
                        Log.d("authorization failed", message)
                        // 2DO show Snackbar
                    }
                )
            }
        )

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

inline fun handleAuthResult(
    result: AuthTabIntent.AuthResult,
    onSuccess: () -> Unit,
    onFail: (String) -> Unit
) {
    if (result.resultCode == AuthTabIntent.RESULT_OK) {
        onSuccess()
    } else {
        onFail(
            when (result.resultCode) {
                AuthTabIntent.RESULT_CANCELED -> "AuthTab canceled."
                AuthTabIntent.RESULT_VERIFICATION_FAILED -> "Verification failed.";
                AuthTabIntent.RESULT_VERIFICATION_TIMED_OUT -> "Verification timed out.";
                else -> "Unknown error."
            }
        )
    }
}
