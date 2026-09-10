package com.demo.data.network.tvshow.api

import com.demo.data.network.common.dto.PaginatedResponseDto
import com.demo.data.network.tvshow.dto.TvShowDto
import kotlinx.serialization.json.Json
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.coroutines.executeAsync

class TvShowServiceImpl(private val okHttpClient: OkHttpClient): TvShowService {

    override suspend fun getTvShows(page: Int): PaginatedResponseDto<TvShowDto> {
        val url = HttpUrl.Builder()
            .addPathSegment("/trending/tv/")
            .addQueryParameter("page", page.toString())
            .build()

        val request = Request
            .Builder()
            .url(url)
            .build()

        val call = okHttpClient.newCall(request)

        return call.executeAsync().use { response ->
            when(response.code) {
                200 -> {
                    Json.decodeFromString<PaginatedResponseDto<TvShowDto>>(response.body.string())
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
                    throw Exception("")
                }
            }
        }
    }
}