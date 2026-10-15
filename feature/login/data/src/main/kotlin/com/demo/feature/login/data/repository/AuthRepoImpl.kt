package com.demo.feature.login.data.repository

import android.util.Log
import androidx.datastore.core.DataStore
import com.demo.core.datastore.proto.Auth
import com.demo.core.datastore.proto.copy
import com.demo.core.domain.api.model.DomainResult
import com.demo.feature.login.data.api.AuthService
import com.demo.feature.login.data.repository.mapper.toDataStoreModel
import com.demo.feature.login.data.repository.mapper.toDomainModel
import com.demo.feature.login.data.repository.mapper.toDomainResult
import com.demo.feature.login.domain.AuthRepo
import com.demo.feature.login.domain.model.RequestToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class AuthRepoImpl @Inject constructor(
    private val authService: AuthService,
    private val dataStore: DataStore<Auth>
): AuthRepo {

    override suspend fun createRequestToken(): DomainResult<RequestToken> =
        authService.createRequestToken().toDomainResult {
            dataStore.updateData { store ->
                store.copy {
                    Log.d("reqToken created", this@toDomainResult.toString())
                    requestToken = toDataStoreModel()
                }
            }.requestToken.toDomainModel()
        }

    override suspend fun createSession(): DomainResult<Unit> {
        val token = dataStore.data.first().requestToken.requestToken

        return authService.createSession(token).toDomainResult {
            dataStore.updateData { store ->
                store.copy {
                    Log.d("session created", this@toDomainResult.toString())
                    session = session_id
                }
            }
        }
    }

    override suspend fun createGuestSession(): DomainResult<Unit> {
        TODO("Not yet implemented")
    }

    override fun authenticationFlow(): Flow<Boolean> = dataStore.data.map { store -> store.session.isNotEmpty() }
}