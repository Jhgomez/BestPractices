package com.demo.feature.tvshow.domain

import com.demo.domain.common.model.Page

interface TvShowRepo {
    fun getTvShow(page: Int): Page<TvShow>
}