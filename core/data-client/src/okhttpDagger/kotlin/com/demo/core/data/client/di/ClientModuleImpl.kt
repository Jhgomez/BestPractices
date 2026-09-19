package com.demo.core.data.client.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module(includes = [OkhttpBaseModule::class])
object ClientModuleImpl: ClientModule<OkHttpClient> {

    @Singleton
    @Provides
    override fun provideClient(builder: OkHttpClient.Builder): OkHttpClient = builder.build()
}