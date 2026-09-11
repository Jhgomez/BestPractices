package com.demo.feature.tvshow.data.api

import com.demo.core.data.api.common.model.PaginatedResponseDto
import com.demo.feature.tvshow.data.model.TvShowDto
import retrofit2.http.GET
import retrofit2.http.Query

interface TvShowServiceImpl: TvShowService {

    @GET("/trending/tv/")
    override suspend fun getTvShows(@Query("page") page: Int): PaginatedResponseDto<TvShowDto>
}