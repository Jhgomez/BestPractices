package com.demo.feature.login.presentation

import android.util.Log
import androidx.compose.foundation.layout.imePadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.demo.core.navigation.viewmodel.AppViewModelFactory
import com.demo.feature.login.presentation.LoginEvent.*

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier
) {
    val vm = viewModel<LoginViewModel>(factory = AppViewModelFactory.current)

    LaunchedEffect(null) {
        vm.events.collect { event ->
            when (event) {
                is NavigateToHome -> {
                    Log.d("User Auth", "Navigate to home triggered")
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