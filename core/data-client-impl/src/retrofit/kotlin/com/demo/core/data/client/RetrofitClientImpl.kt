package com.demo.core.data.client

import com.demo.data.client.AuthInterceptor
import com.demo.data.client.BuildConfig
import kotlinx.serialization.json.Json
import okhttp3.ConnectionPool
import okhttp3.Dispatcher
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.time.Duration
import java.util.concurrent.TimeUnit

val dispatcher = Dispatcher().apply {
    maxRequestsPerHost = 128
    maxRequests = 128
}

val connectionPool = ConnectionPool(
    maxIdleConnections = 16,
    keepAliveDuration = 60,
    timeUnit = TimeUnit.SECONDS
)

val json = Json {
    explicitNulls = false
    prettyPrint = true
    ignoreUnknownKeys = true
    coerceInputValues = true
    allowTrailingComma = true
    allowComments = true
    isLenient = true
}

// retrofit includes okhttp as a transient dependency(built on top of okhttp)
val client =  OkHttpClient
    .Builder()
    .addInterceptor(AuthInterceptor())
    .addInterceptor(HttpLoggingInterceptor())
    .dispatcher(dispatcher)
    .connectionPool(connectionPool)
    .connectTimeout(Duration.ofSeconds(4))
    .callTimeout(Duration.ofSeconds(16))
    .build()

val retrofit = Retrofit
    .Builder()
    .baseUrl(BuildConfig.BASE_URL)
    .client(client)
    .addConverterFactory(
        json.asConverterFactory("application/json; charset=utf-8".toMediaType())
    )
    .build()