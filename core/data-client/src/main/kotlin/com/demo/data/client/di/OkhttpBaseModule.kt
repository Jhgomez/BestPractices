package com.demo.data.client.di

import com.demo.data.client.AuthInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.ConnectionPool
import okhttp3.Dispatcher
import okhttp3.OkHttpClient.Builder
import okhttp3.logging.HttpLoggingInterceptor
import java.time.Duration
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
internal interface OkhttpBaseModule {

    @Singleton
    @Provides
    fun providesOkhttpConfiguredBaseBuilder(): Builder {
        val dispatcher = Dispatcher().apply {
            maxRequestsPerHost = 8
            maxRequests = 16
        }

        val connectionPool = ConnectionPool(
            maxIdleConnections = 16,
            keepAliveDuration = 60,
            timeUnit = TimeUnit.SECONDS
        )

        return Builder()
            .addInterceptor(HttpLoggingInterceptor())
            .addInterceptor(AuthInterceptor())
            .dispatcher(dispatcher)
            .connectionPool(connectionPool)
            .connectTimeout(Duration.ofSeconds(6))
            .callTimeout(Duration.ofSeconds(16))
    }
}