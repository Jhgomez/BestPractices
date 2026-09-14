package com.demo.core.data.client.di

import com.demo.core.data.client.BaseUrlInterceptor
import com.demo.data.client.di.OkhttpBaseModule
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

interface yo
@Module(includes = [OkhttpBaseModule::class])
object ClientModule {

    @Singleton
    @Provides
    fun provideClient(builder: OkHttpClient.Builder): OkHttpClient =
        builder
            .addInterceptor(BaseUrlInterceptor())
            .build()
}