package com.demo.feature.tvshow.data.api

import com.demo.core.data.client.utils.get
import com.demo.core.data.model.common.PaginatedResponseDto
import com.demo.feature.tvshow.data.model.TvShowDto
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