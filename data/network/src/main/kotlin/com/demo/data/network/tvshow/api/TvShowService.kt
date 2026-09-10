package com.demo.data.network.tvshow.api

import com.demo.data.network.models.PaginatedResponseDto
import com.demo.data.network.models.TvShowDto

interface TvShowService {
    fun getTvShows(page: Int, pageSize: Int): PaginatedResponseDto<TvShowDto>
}