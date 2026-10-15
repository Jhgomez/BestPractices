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
import com.demo.feature.login.domain.model.RequestToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

internal data class LoginScreeState(
    val isUsernameWrong: Boolean = false,
    val isPasswordWrong: Boolean = false,
    val isTokenRequestLoading: Boolean = false
)

internal sealed interface LoginIntent
internal data class Login(val username: String, val password: String): LoginIntent
internal object UpdateUserNameInput: LoginIntent
internal object UpdatePasswordInput: LoginIntent

internal sealed interface LoginEvent
@JvmInline
internal value class RedirectToAuthorizeToken(val token: String) : LoginEvent
@JvmInline
internal value class NavigateToHome(val placeHolder: String = ""): LoginEvent
@JvmInline
internal value class ShowSnackBar(val message: String): LoginEvent



internal class LoginViewModel @Inject constructor(private val repo: AuthRepo): ViewModel() {

    internal val state: State<LoginScreeState>
        field = mutableStateOf(LoginScreeState())

    private val eventChannel = Channel<LoginEvent>(Channel.BUFFERED)
    internal val events = eventChannel.receiveAsFlow().flowOn(Dispatchers.Default)

    internal val authenticationFlow = repo
        .authenticationFlow()
        .flowOn(Dispatchers.Default)
        .distinctUntilChanged()

    internal fun handleIntent(intent: LoginIntent) {
        when (intent) {
            is Login -> login(username = intent.username, password = intent.password)
            UpdatePasswordInput ->
                if (state.value.isPasswordWrong) {
                    state.value = state.value.copy(
                        isPasswordWrong = false
                    )
                }
            UpdateUserNameInput ->
                if (state.value.isUsernameWrong) {
                    state.value = state.value.copy(
                        isUsernameWrong = false
                    )
                }
        }
    }

    private fun login(username: String, password: String) {
        if (state.value.isTokenRequestLoading) {
            return
        }

        if (username.isEmpty() || password.isEmpty()) {
            state.value = state.value.copy(
                isPasswordWrong = true,
                isUsernameWrong = true,
                isTokenRequestLoading = false
            )

            return
        }

        state.value = state.value.copy(isTokenRequestLoading = true)

        viewModelScope.launch {
            Log.d("User Auth", "launch request")
            when (val token = repo.createRequestToken()) {
                is DomainBadRequest<RequestToken>,
                is DomainInternalServerError<RequestToken>,
                is DomainNetworkError<RequestToken>,
                is DomainUnauthorized<RequestToken>,
                is DomainUnhandledHttpCode<RequestToken> -> {
                    Log.d("User Auth err", token.toString() + " " + token.code + " " + token.message)
                    state.value = state.value.copy(
                        isPasswordWrong = true,
                        isUsernameWrong = true,
                        isTokenRequestLoading = false
                    )
                }
                is DomainSuccess<RequestToken> -> {
                    Log.d("User Auth", "U $username P $password")

                    state.value = state.value.copy(isTokenRequestLoading = false)

                    eventChannel.send(
                        RedirectToAuthorizeToken(token.result.token)
                    )
                }
            }
        }
    }
}