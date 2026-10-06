package com.demo.feature.login.data.model

import android.util.Log
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

object TMDBInstantSerializer: KSerializer<Instant> {
    @OptIn(ExperimentalTime::class)
    override val descriptor: SerialDescriptor
        get() = Instant.serializer().descriptor

    override fun serialize(encoder: Encoder, value: Instant) {
        encoder.encodeString(value.toString())
    }

    override fun deserialize(decoder: Decoder): Instant {
        // this is how TMBD return times in its "authentication/guest_session/new" service: "2016-08-27 16:26:40 UTC"
        // this should result in "2016-08-27T16:26:40Z" which is the ISO format for date time in UTC
        val instantString = decoder.decodeString()
            .replaceFirst(" ", "T")
            .replaceFirst(" ", "Z")
            .substring(0..20)

        Log.d("UTC to Inst", instantString)

        return Instant.parse(instantString)
    }

}