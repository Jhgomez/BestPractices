package com.demo.feature.login.presentation

import android.net.Uri
import android.util.Log
import androidx.browser.auth.AuthTabIntent
import androidx.browser.customtabs.CustomTabsClient
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import com.demo.core.presentation.LocalAppViewModelFactory
import com.demo.core.presentation.LocalLoginActivityResultLauncher
import com.demo.core.presentation.R
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier
) {
    val vm = viewModel<LoginViewModel>(factory = LocalAppViewModelFactory.current)

    val mLauncher = LocalLoginActivityResultLauncher.current
    val context = LocalContext.current

    val browser = CustomTabsClient.getPackageName(context, null)
    // if null, custom tabs not supported
    val redirectUri = if (browser == null)
        stringResource(R.string.redirect_uri)
    else
        stringResource(R.string.redirect_uri_auth_tab)

    LaunchedEffect(null) {

        vm.events.collect { event ->
            when (event) {
                is RedirectToAuthorizeToken -> {
                    val url =
                        "https://www.themoviedb.org/authenticate/${event.token}?redirect_to=${Uri.encode(redirectUri)}"

                    if (browser == null) {
                        val customTabIntent = CustomTabsIntent
                            .Builder()
                            .build()

                        customTabIntent.launchUrl(context, url.toUri())
                    } else {
                        val authTabIntent = AuthTabIntent
                            .Builder()
                            .build()

                        authTabIntent.launch(mLauncher, url.toUri(), redirectUri.substringBefore(":"))
                    }
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
