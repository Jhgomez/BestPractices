package com.demo.domain.tvshow

import com.demo.domain.common.model.Page

interface TvShowRepo {
    fun getTvShow(page: Int): Page<TvShow>
}