package com.demo.feature.login.data.repository.mapper

import com.demo.core.data.client.api.BadRequest
import com.demo.core.data.client.api.DataResult
import com.demo.core.data.client.api.InternalServerError
import com.demo.core.data.client.api.NetworkError
import com.demo.core.data.client.api.Success
import com.demo.core.data.client.api.Unauthorized
import com.demo.core.data.client.api.UnhandledHttpCode
import com.demo.core.datastore.proto.Token
import com.demo.core.domain.api.model.DomainBadRequest
import com.demo.core.domain.api.model.DomainInternalServerError
import com.demo.core.domain.api.model.DomainNetworkError
import com.demo.core.domain.api.model.DomainResult
import com.demo.core.domain.api.model.DomainSuccess
import com.demo.core.domain.api.model.DomainUnauthorized
import com.demo.core.domain.api.model.DomainUnhandledHttpCode
import com.demo.feature.login.data.model.RequestTokenResponseDto
import com.demo.feature.login.domain.model.RequestToken

fun Token.toDomainModel(): RequestToken = RequestToken(
    token = requestToken,
    expiresAt = expiresAt
)

fun RequestTokenResponseDto.toDataStoreModel(): Token = Token.newBuilder().let { builder ->
    builder.requestToken = request_token
    builder.expiresAt = expires_at.toEpochMilliseconds()
    builder.build()
}

suspend fun <T, R> DataResult<T>.toDomainResult(successMapper: suspend T.() -> R): DomainResult<R> =
    when(this) {
        is BadRequest<T> -> DomainBadRequest(code = code, message = message)
        is InternalServerError<T> -> DomainInternalServerError(code = code, message = message)
        is NetworkError<T> -> DomainNetworkError(message = message)
        is Unauthorized<T> -> DomainUnauthorized(code = code, message = message)
        is UnhandledHttpCode<T> -> DomainUnhandledHttpCode(code = code, message = message)
        is Success<T> -> DomainSuccess(successMapper(this.result))
    }