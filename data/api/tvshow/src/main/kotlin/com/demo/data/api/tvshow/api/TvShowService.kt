package com.demo.data.network.tvshow.api

import com.demo.data.network.common.dto.PaginatedResponseDto
import com.demo.data.network.tvshow.dto.TvShowDto

interface TvShowService {
    suspend fun getTvShows(page: Int): PaginatedResponseDto<TvShowDto>
}