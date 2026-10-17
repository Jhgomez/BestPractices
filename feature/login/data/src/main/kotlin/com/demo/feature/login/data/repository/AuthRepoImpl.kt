package com.demo.feature.login.data.repository

import android.util.Log
import androidx.datastore.core.DataStore
import com.demo.core.datastore.proto.Auth
import com.demo.core.datastore.proto.copy
import com.demo.core.domain.api.model.DomainResult
import com.demo.feature.login.data.api.AuthService
import com.demo.feature.login.data.repository.mapper.toDataStoreModel
import com.demo.feature.login.data.repository.mapper.toDomainModel
import com.demo.feature.login.data.repository.mapper.handleResponse
import com.demo.feature.login.domain.AuthRepo
import com.demo.feature.login.domain.model.DomainGuestSession
import com.demo.feature.login.domain.model.RequestToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.time.Clock

@Singleton
internal class AuthRepoImpl @Inject constructor(
    private val authService: AuthService,
    private val dataStore: DataStore<Auth>
): AuthRepo {

    override suspend fun createRequestToken(): DomainResult<RequestToken> =
        authService.createRequestToken().handleResponse {
            dataStore.updateData { store ->
                store.copy {
                    Log.d("reqToken created", this@handleResponse.toString())
                    requestToken = this@handleResponse.toDataStoreModel()
                }
            }.requestToken.toDomainModel()
        }

    override suspend fun createSession(): DomainResult<Unit> {
        val token = dataStore.data.first().requestToken.requestToken

        return authService.createSession(token).handleResponse {
            dataStore.updateData { store ->
                store.copy {
                    Log.d("User Auth", "session created  ${this@handleResponse}")
                    session = session_id
                }
            }
        }
    }

    override suspend fun createGuestSession(): DomainResult<DomainGuestSession> =
        authService.createGuestSession().handleResponse {
            dataStore.updateData { store ->
                Log.d("GuestSesh", "session created  ${this@handleResponse}")
                store.copy {
                    guestSession = this@handleResponse.toDataStoreModel()
                }
            }.guestSession.toDomainModel()
        }

    override fun authenticationFlow(): Flow<Boolean> = dataStore.data.map {
        store ->
            store.session.isNotEmpty() ||
                    (store.guestSession.guestSessionId.isNotEmpty() &&
                            store.guestSession.expiresAt > Clock.System.now().toEpochMilliseconds())
    }
}