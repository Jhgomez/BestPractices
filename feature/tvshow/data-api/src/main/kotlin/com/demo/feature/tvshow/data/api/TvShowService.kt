package com.demo.feature.tvshow.data.api

import com.demo.core.data.api.common.model.PaginatedResponseDto
import com.demo.feature.tvshow.data.model.TvShowDto

interface TvShowService {
    suspend fun getTvShows(page: Int): PaginatedResponseDto<TvShowDto>
}