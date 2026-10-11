package com.demo.feature.login.data.repository

import androidx.datastore.core.DataStore
import com.demo.core.data.client.api.BadRequest
import com.demo.core.data.client.api.InternalServerError
import com.demo.core.data.client.api.NetworkError
import com.demo.core.data.client.api.Success
import com.demo.core.data.client.api.Unauthorized
import com.demo.core.data.client.api.UnhandledHttpCode
import com.demo.core.datastore.proto.Auth
import com.demo.core.datastore.proto.copy
import com.demo.core.domain.api.model.DomainBadRequest
import com.demo.core.domain.api.model.DomainResult
import com.demo.core.domain.api.model.DomainSuccess
import com.demo.feature.login.data.api.AuthService
import com.demo.feature.login.data.model.RequestTokenResponseDto
import com.demo.feature.login.data.repository.mapper.toDataStoreModel
import com.demo.feature.login.domain.AuthRepo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepoImpl @Inject constructor(
    private val authService: AuthService,
    private val dataStore: DataStore<Auth>
): AuthRepo {

    override suspend fun createRequestToken(): DomainResult<Unit> =
        when (val requestToken = authService.createRequestToken()) {
            is BadRequest<RequestTokenResponseDto> -> DomainBadRequest()
            is InternalServerError<RequestTokenResponseDto> -> DomainSuccess(Unit)
            is NetworkError<RequestTokenResponseDto> -> DomainSuccess(Unit)
            is Unauthorized<RequestTokenResponseDto> -> DomainSuccess(Unit)
            is UnhandledHttpCode<RequestTokenResponseDto> -> DomainSuccess(Unit)
            is Success<RequestTokenResponseDto> -> {
                dataStore.updateData { store ->
                    store.copy {
                        this.requestToken = requestToken.result.toDataStoreModel()
                    }
                }

                DomainSuccess(Unit)
            }
        }

    override suspend fun createGuestSession(): DomainResult<Unit> {
        TODO("Not yet implemented")
    }
}