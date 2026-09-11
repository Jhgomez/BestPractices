package com.demo.feature.tvshow.data.api

import com.demo.data.api.common.dto.PaginatedResponseDto
import com.demo.data.network.tvshow.dto.TvShowDto

interface TvShowService {
    suspend fun getTvShows(page: Int): PaginatedResponseDto<TvShowDto>
}