package com.demo.feature.login.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.core.domain.api.model.DomainBadRequest
import com.demo.core.domain.api.model.DomainInternalServerError
import com.demo.core.domain.api.model.DomainNetworkError
import com.demo.core.domain.api.model.DomainSuccess
import com.demo.core.domain.api.model.DomainUnauthorized
import com.demo.core.domain.api.model.DomainUnhandledHttpCode
import com.demo.feature.login.domain.AuthRepo
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

internal data class LoginScreeState(
    val isUsernameWrong: Boolean = false,
    val isPasswordWrong: Boolean = false,
)

internal sealed interface LoginIntent {
    data class Login(val username: String, val password: String): LoginIntent

    object UpdateUserNameInput: LoginIntent

    object UpdatePasswordInput: LoginIntent
}

internal sealed interface LoginEvent {
    class NavigateToHome : LoginEvent
}

internal class LoginViewModel @Inject constructor(private val repo: AuthRepo): ViewModel() {

    internal val state: State<LoginScreeState>
        field = mutableStateOf(LoginScreeState())

    private val eventChannel = Channel<LoginEvent>(Channel.BUFFERED)
    internal val events = eventChannel.receiveAsFlow()

    internal fun handleIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.Login -> login(username = intent.username, password = intent.password)
            LoginIntent.UpdatePasswordInput ->
                if (state.value.isPasswordWrong) {
                    state.value = state.value.copy(
                        isPasswordWrong = false
                    )
                }
            LoginIntent.UpdateUserNameInput ->
                if (state.value.isUsernameWrong) {
                    state.value = state.value.copy(
                        isUsernameWrong = false
                    )
                }
        }
    }

    private fun login(username: String, password: String) {
        viewModelScope.launch {
            when (repo.createRequestToken()) {
                is DomainBadRequest<Unit>,
                is DomainInternalServerError<Unit>,
                is DomainNetworkError<Unit>,
                is DomainUnauthorized<Unit>,
                is DomainUnhandledHttpCode<Unit> ->
                    state.value = state.value.copy(isPasswordWrong = true, isUsernameWrong = true)
                is DomainSuccess<Unit> -> {
                    Log.d("User Auth", "U $username P $password")
                    eventChannel.send(LoginEvent.NavigateToHome())
                }
            }
        }
    }

}