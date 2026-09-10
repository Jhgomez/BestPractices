package com.demo.data.network.tvshow.api

import com.demo.data.network.common.dto.PaginatedResponseDto
import com.demo.data.network.tvshow.dto.TvShowDto
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
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

        call.executeAsync().use {
            // https://github.com/lysine-dev/okhttp/blob/1f04bf8028b0fd9471ba9a77eba0ad913f86705a/samples/tlssurvey/src/main/kotlin/okhttp3/survey/Iana.kt#L21
        }
    }
}