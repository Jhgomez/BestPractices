package com.demo.data.network

import android.content.Context
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File

val client =  OkHttpClient
    .Builder()
    .addInterceptor(HttpLoggingInterceptor())
    .addInterceptor(BaseUrlInterceptor())
    .addInterceptor(AuthInterceptor())
    .build()
class BaseUrlInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        client.newCall(
            
        )

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