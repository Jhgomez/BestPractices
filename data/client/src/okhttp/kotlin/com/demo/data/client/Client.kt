package com.demo.data.network

import okhttp3.ConnectionPool
import okhttp3.Dispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
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

class BaseUrlInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val request = originalRequest
            .newBuilder()
            .url(BuildConfig.BASE_URL + originalRequest.url)
            .build()

        return chain.proceed(request)
    }
}

class AuthInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val request = originalRequest
            .newBuilder()
            .header("Authorization: Bearer", BuildConfig.ACCESS_TOKEN)
            .method(originalRequest.method, originalRequest.body)
            .build()

        return chain.proceed(request)
    }

}