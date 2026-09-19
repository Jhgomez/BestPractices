package com.demo.core.data.client.common

import com.demo.data.client.utils.BuildConfig
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.coroutines.executeAsync
import kotlin.reflect.KType

//private val dispatcher = Dispatcher().apply {
//    maxRequestsPerHost = 128
//    maxRequests = 128
//}
//
//private val connectionPool = ConnectionPool(
//    maxIdleConnections = 16,
//    keepAliveDuration = 60,
//    timeUnit = TimeUnit.SECONDS
//)
//
//val clientDep =  OkHttpClient
//    .Builder()
//    .addInterceptor(HttpLoggingInterceptor())
//    .addInterceptor(BaseUrlInterceptor())
//    .addInterceptor(AuthInterceptor())
//    .dispatcher(dispatcher)
//    .connectionPool(connectionPool)
//    .connectTimeout(Duration.ofSeconds(4))
//    .callTimeout(Duration.ofSeconds(16))
//    .build()

val json = Json {
    explicitNulls = false
    prettyPrint = true
    ignoreUnknownKeys = true
    coerceInputValues = true
    allowTrailingComma = true
    allowComments = true
    isLenient = true
    encodeDefaults = true
}

val urlBuilder = BuildConfig.BASE_URL.toHttpUrl().newBuilder()

suspend fun <T: Any> OkHttpClient.get(
    path: String,
    serializer: KSerializer<T>,
    vararg params: Pair<String, String>
): T {
    urlBuilder.apply {
        addPathSegment(path)

        for (param in params) {
            addQueryParameter(param.first, param.second)
        }
    }

    val request = Request
        .Builder()
        .url(urlBuilder.build())
        .build()

    val call = newCall(request)

    return call.executeAsync().use { response ->
        when(response.code) {
            200 -> {
                json.decodeFromString(string = response.body.string(), deserializer = serializer)
            }
            400 -> {
                // bad request
                throw Exception("")
            }
            401 -> {
                // unauthorized
                throw Exception("")
            }
            500 -> {
                // internal server error
                throw Exception("")
            }
            else -> {
                throw Exception(response.body.string())
            }
        }
    }
}