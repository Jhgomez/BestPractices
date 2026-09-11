package com.demo.feature.tvshow.data.api

import com.demo.data.client.AppHttpClient
import com.demo.data.api.common.dto.PaginatedResponseDto
import com.demo.data.network.tvshow.dto.TvShowDto
import kotlin.reflect.typeOf

class TvShowServiceImpl(private val httpClient: AppHttpClient): TvShowService {

    override suspend fun getTvShows(page: Int): PaginatedResponseDto<TvShowDto> =
        httpClient.get(
            path = "/trending/tv/",
            kType = typeOf<PaginatedResponseDto<TvShowDto>>(),
            Pair("page", page.toString())
        )
}