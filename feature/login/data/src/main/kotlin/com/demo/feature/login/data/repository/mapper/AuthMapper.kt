package com.demo.feature.login.data.repository.mapper

import com.demo.core.datastore.proto.Token
import com.demo.feature.login.data.model.RequestTokenResponseDto

fun RequestTokenResponseDto.toDataStoreModel() = Token.newBuilder().let { builder ->
    builder.requestToken = request_token
    builder.expiresAt = expires_at.toEpochMilliseconds()
    builder.build()
}
