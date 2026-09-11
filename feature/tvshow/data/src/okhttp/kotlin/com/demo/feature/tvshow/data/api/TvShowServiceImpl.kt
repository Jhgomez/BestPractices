package com.demo.feature.tvshow.data.api

import com.demo.core.data.api.common.dto.PaginatedResponseDto
import com.demo.core.data.client.get
import com.demo.feature.tvshow.data.dto.TvShowDto
import okhttp3.OkHttpClient
import kotlin.reflect.typeOf

class TvShowServiceImpl(private val httpClient: OkHttpClient): TvShowService {

    override suspend fun getTvShows(page: Int): PaginatedResponseDto<TvShowDto> =
        httpClient.get(
            path = "/trending/tv/",
            kType = typeOf<PaginatedResponseDto<TvShowDto>>(),
            Pair("page", page.toString())
        )
}