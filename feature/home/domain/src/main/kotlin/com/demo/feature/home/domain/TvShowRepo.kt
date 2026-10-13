package com.demo.feature.home.domain

interface TvShowRepo {
    suspend fun getTvShow(page: Int): Unit
}