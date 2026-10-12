package com.demo.feature.login.presentation

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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

internal data class LoginScreeState(
    val userName: String = "",
    val wrongUserName: Boolean = false,
    val password: String = "",
    val wrongPassword: Boolean = false,
)

internal sealed interface LoginIntent {
    object Login: LoginIntent

    @JvmInline
    value class UpdateUserNameInput(val userName: String): LoginIntent

    @JvmInline
    value class UpdatePasswordInput(val password: String): LoginIntent
}

internal sealed interface LoginEvent {
    class NavigateToHome : LoginEvent
}

class LoginViewModel @Inject constructor(private val repo: AuthRepo): ViewModel() {

    internal val state: State<LoginScreeState>
        field = mutableStateOf(LoginScreeState())

    private val eventChannel = Channel<LoginEvent>(Channel.BUFFERED)
    internal val events = eventChannel.receiveAsFlow()

    internal fun handleIntent(intent: LoginIntent) {
        when (intent) {
            LoginIntent.Login -> login()
            is LoginIntent.UpdatePasswordInput ->
                state.value = state.value.copy(
                    password = intent.password,
                    wrongPassword = false
                )
            is LoginIntent.UpdateUserNameInput ->
                state.value = state.value.copy(
                    userName = intent.userName,
                    wrongUserName = false
                )
        }
    }

    private fun login() {
        viewModelScope.launch {
            when (repo.createRequestToken()) {
                is DomainBadRequest<Unit>,
                is DomainInternalServerError<Unit>,
                is DomainNetworkError<Unit>,
                is DomainUnauthorized<Unit>,
                is DomainUnhandledHttpCode<Unit> ->
                    state.value = state.value.copy(wrongPassword = true, wrongUserName = true)
                is DomainSuccess<Unit> -> eventChannel.send(LoginEvent.NavigateToHome())
            }
        }
    }

}