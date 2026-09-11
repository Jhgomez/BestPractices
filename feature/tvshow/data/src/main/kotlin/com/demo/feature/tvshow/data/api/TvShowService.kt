package com.demo.feature.tvshow.data.api

import com.demo.core.data.api.common.dto.PaginatedResponseDto
import com.demo.feature.tvshow.data.dto.TvShowDto

interface TvShowService {
    suspend fun getTvShows(page: Int): PaginatedResponseDto<TvShowDto>
}