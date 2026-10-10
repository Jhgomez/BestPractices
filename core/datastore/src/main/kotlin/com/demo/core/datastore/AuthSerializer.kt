package com.demo.core.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.demo.core.datastore.proto.Auth
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

internal object AuthSerializer : Serializer<Auth> {
    override val defaultValue: Auth = Auth.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): Auth {
        try {
            return Auth.parseFrom(input)
        } catch (e: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", e)
        }
    }

    override suspend fun writeTo(
        t: Auth,
        output: OutputStream
    ) {
        t.writeTo(output)
    }
}