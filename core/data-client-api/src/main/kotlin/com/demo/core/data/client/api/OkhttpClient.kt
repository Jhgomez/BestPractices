package com.demo.core.data.client.api

import com.demo.data.client.api.R
import com.demo.data.client.api.BuildConfig
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.coroutines.executeAsync
import okio.IOException
import kotlin.coroutines.cancellation.CancellationException

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
): DataResult<T> {
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

    return try {
        call.executeAsync().use { response ->
            when (response.code) {
                in 200..299 -> {
                    Success(
                        result = json.decodeFromString(
                            string = response.body.string(),
                            deserializer = serializer
                        )
                    )
                }

                400 -> {
                    BadRequest(
                        code = response.code.toShort(),
                        message = response.body.toString()
                    )
                }

                401 -> {
                    Unauthorized(
                        code = response.code.toShort(),
                        message = response.body.toString()
                    )
                }

                500 -> {
                    InternalServerError(
                        code = response.code.toShort(),
                        message = response.body.toString()
                    )
                }
                else -> {
                    UnhandledHttpCode(
                        code = response.code.toShort(),
                        message = response.body.toString()
                    )
                }
            }
        }
    } catch (exception: IOException) {
        NetworkError(
            message = exception.message ?: "IOException"
        )
    } catch (exception: CancellationException) {
        // I could have left this uncaught, but I want it to be visually explicit
        throw exception
    }
}