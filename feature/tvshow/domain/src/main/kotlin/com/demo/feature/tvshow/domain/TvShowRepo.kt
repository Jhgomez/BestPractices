package com.demo.feature.tvshow.domain

import com.demo.core.domain.common.model.Page

interface TvShowRepo {
    suspend fun getTvShow(page: Int): Page<TvShow>
}