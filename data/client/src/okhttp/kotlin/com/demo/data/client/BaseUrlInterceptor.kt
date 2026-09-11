package com.demo.data.client

import okhttp3.Interceptor
import okhttp3.Response

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
