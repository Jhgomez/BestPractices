package com.demo.core.data.client

import com.demo.data.client.AppHttpClient
import com.demo.data.client.AuthInterceptor
import com.demo.data.client.BuildConfig
import okhttp3.ConnectionPool
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.time.Duration
import java.util.concurrent.TimeUnit

class RetrofitClientImpl: AppHttpClient {
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
        .build()

    override fun <T> createService(serviceClass: Class<T>): T =
        retrofit.create(serviceClass)
}