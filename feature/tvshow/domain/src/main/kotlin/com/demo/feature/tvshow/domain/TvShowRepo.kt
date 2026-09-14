package com.demo.feature.tvshow.domain

import com.demo.core.domain.common.model.Page

interface TvShowRepo {
    fun getTvShow(page: Int): Page<TvShow>
}