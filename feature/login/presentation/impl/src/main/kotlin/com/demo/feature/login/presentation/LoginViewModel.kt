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
import com.demo.feature.login.domain.model.DomainGuestSession
import com.demo.feature.login.domain.model.RequestToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

internal data class LoginScreeState(
    val isRequestLoading: Boolean = false
)

internal sealed interface LoginIntent
@JvmInline
internal value class TMDBLogin(val placeholder: String = ""): LoginIntent
@JvmInline
internal value class GuestLogin(val placeholder: String = ""): LoginIntent

internal sealed interface LoginEvent
@JvmInline
internal value class RedirectToAuthorizeToken(val token: String) : LoginEvent
@JvmInline
internal value class ShowSnackBar(val message: String): LoginEvent



internal class LoginViewModel @Inject constructor(private val repo: AuthRepo): ViewModel() {

    internal val state: State<LoginScreeState>
        field = mutableStateOf(LoginScreeState())

    private val eventChannel = Channel<LoginEvent>(Channel.BUFFERED)
    internal val events = eventChannel.receiveAsFlow().flowOn(Dispatchers.Default)

    internal fun handleIntent(intent: LoginIntent) = 
        when (intent) {
            is TMDBLogin -> doTMDBLogin()
            is GuestLogin -> doGuestLogin()
        }

    private fun doGuestLogin() {
        if (state.value.isRequestLoading) {
            return
        }

        state.value = state.value.copy(isRequestLoading = true)

        viewModelScope.launch {
            when (val response = repo.createGuestSession()) {
                is DomainBadRequest<DomainGuestSession>,
                is DomainInternalServerError<DomainGuestSession>,
                is DomainNetworkError<DomainGuestSession>,
                is DomainUnauthorized<DomainGuestSession>,
                is DomainUnhandledHttpCode<DomainGuestSession> -> {
                    Log.d("GuestSesh err", response.toString() + " " + response.code + " " + response.message)
                    state.value = state.value.copy(isRequestLoading = false)
                }
                is DomainSuccess<DomainGuestSession> -> {
                    // we don't do anything here, the activity is listening for authentication state
                    // changes, and the navigation entries change based on auth state
                    Log.d("GuestSesh", "created")
                }
            }
        }
    }

    private fun doTMDBLogin() {
        if (state.value.isRequestLoading) {
            return
        }

        state.value = state.value.copy(isRequestLoading = true)

        viewModelScope.launch {
            Log.d("User Auth", "launch request")
            when (val token = repo.createRequestToken()) {
                is DomainBadRequest<RequestToken>,
                is DomainInternalServerError<RequestToken>,
                is DomainNetworkError<RequestToken>,
                is DomainUnauthorized<RequestToken>,
                is DomainUnhandledHttpCode<RequestToken> -> {
                    Log.d("User Auth err", token.toString() + " " + token.code + " " + token.message)
                    state.value = state.value.copy(isRequestLoading = false)
                }
                is DomainSuccess<RequestToken> -> {
                    Log.d("User Auth", "Token created")

                    state.value = state.value.copy(isRequestLoading = false)

                    eventChannel.send(
                        RedirectToAuthorizeToken(token.result.token)
                    )
                }
            }
        }
    }
}