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
            // readFrom is already called on the data store background thread
            return Auth.parseFrom(input)
        } catch (e: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", e)
        }
    }

    override suspend fun writeTo(
        t: Auth,
        output: OutputStream
    ) {
        // writeTo is already called on the data store background thread
        t.writeTo(output)
    }
}