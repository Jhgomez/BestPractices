package com.demo.data.client

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import okhttp3.ConnectionPool
import okhttp3.Dispatcher
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.coroutines.executeAsync
import okhttp3.logging.HttpLoggingInterceptor
import java.time.Duration
import java.util.concurrent.TimeUnit
import kotlin.reflect.KClass
import kotlin.reflect.KType

class OkhttpClientImpl: AppHttpClient {
    val dispatcher = Dispatcher().apply {
        maxRequestsPerHost = 128
        maxRequests = 128
    }

    val connectionPool = ConnectionPool(
        maxIdleConnections = 16,
        keepAliveDuration = 60,
        timeUnit = TimeUnit.SECONDS
    )

    val client =  OkHttpClient
        .Builder()
        .addInterceptor(HttpLoggingInterceptor())
        .addInterceptor(BaseUrlInterceptor())
        .addInterceptor(AuthInterceptor())
        .dispatcher(dispatcher)
        .connectionPool(connectionPool)
        .connectTimeout(Duration.ofSeconds(4))
        .callTimeout(Duration.ofSeconds(16))
        .build()

    override suspend fun <T> get(
        path: String,
        vararg params: Pair<String, String>,
        kType: KType
    ): T {
        val url = HttpUrl.Builder().apply {
            addPathSegment("/trending/tv/")

            for (param in params) {
                addQueryParameter(param.first, param.second)
            }
        }.build()

        val request = Request
            .Builder()
            .url(url)
            .build()

        val call = client.newCall(request)

        return call.executeAsync().use { response ->
            when(response.code) {
                200 -> {
                    val serializer = serializer(kType) as KSerializer<T>

                    Json.decodeFromString(string = response.body.string(), deserializer = serializer)
                }
                400 -> {
                    // bad request
                    throw Exception("")
                }
                401 -> {
                    // unauthorized
                    throw Exception("")
                }
                500 -> {
                    // internal server error
                    throw Exception("")
                }
                else -> {
                    throw Exception("")
                }
            }
        }
    }
}