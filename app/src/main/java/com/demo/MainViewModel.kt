package com.demo

import android.util.Log
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

data class MainState(
    val count: Int = 0,
    val message: String = "Just a message"
)

interface MainActivityEvent {
    @JvmInline
    value class ShowSnackBar(val message: String): MainActivityEvent
}

class MainViewModel @Inject constructor(
    private val repo: AuthRepo
): ViewModel() {
    val state = mutableStateOf(MainState())

    private val eventChannel = Channel<MainActivityEvent>(Channel.BUFFERED)
    val events = eventChannel.receiveAsFlow()

    fun addCounter() {
        state.value = state.value.copy(count = state.value.count + 1)
    }

    /**
     * Creates session
     */
    fun onTokenAuthorization() {
        viewModelScope.launch {
            val response = repo.createSession()

            if (response is DomainSuccess<Unit>) return@launch

            eventChannel.send(
                when (response) {
                    is DomainBadRequest<Unit>,
                    is DomainInternalServerError<Unit>,
                    is DomainNetworkError<Unit>,
                    is DomainUnauthorized<Unit>,
                    is DomainUnhandledHttpCode<Unit> -> {
                        Log.d("Error creating session", "${response.code} ${response.message}")
                        MainActivityEvent.ShowSnackBar(message = response.message)
                    }
                    is DomainSuccess<Unit> -> {
                        // success is handled reactively. The login viewmodel is listening for
                        // changes in datastore's "session" property
                        throw IllegalStateException("Impossible State")
                    }
                }
            )
        }
    }
}