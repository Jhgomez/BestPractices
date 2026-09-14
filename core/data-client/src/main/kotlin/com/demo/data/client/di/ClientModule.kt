package com.demo.data.client.di

import okhttp3.OkHttpClient

internal interface ClientModule<T> {
    fun provideClient(builder: OkHttpClient.Builder): T
}