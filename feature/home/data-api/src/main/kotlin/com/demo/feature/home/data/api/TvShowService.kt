package com.demo.feature.home.data.api

import com.demo.core.data.model.common.PaginatedResponseDto
import com.demo.feature.home.data.model.TvShowDto

interface TvShowService {
    suspend fun getTvShows(page: Int): PaginatedResponseDto<TvShowDto>
}