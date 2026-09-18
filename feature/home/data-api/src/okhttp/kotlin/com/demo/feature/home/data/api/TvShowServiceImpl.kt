package com.demo.feature.home.data.api

import com.demo.core.data.client.utils.get
import com.demo.core.data.model.common.PaginatedResponseDto
import com.demo.feature.home.data.model.TvShowDto
import okhttp3.OkHttpClient
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.reflect.typeOf

@Singleton
internal class TvShowServiceImpl @Inject constructor(private val httpClient: OkHttpClient):
    TvShowService {

    override suspend fun getTvShows(page: Int): PaginatedResponseDto<TvShowDto> =
        httpClient.get(
            path = "/trending/tv/",
            kType = typeOf<PaginatedResponseDto<TvShowDto>>(),
            Pair("page", page.toString())
        )
}