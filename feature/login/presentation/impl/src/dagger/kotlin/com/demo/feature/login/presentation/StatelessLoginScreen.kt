package com.demo.feature.login.presentation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
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
        username = vm.state.value.userName,
        password = vm.state.value.password,
        handleIntent = { intent -> vm.handleIntent(intent) },
        didLoginFail = vm.state.value.wrongPassword || vm.state.value.wrongUserName
    )
}