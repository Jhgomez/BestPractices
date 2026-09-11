package com.demo.data.client

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

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