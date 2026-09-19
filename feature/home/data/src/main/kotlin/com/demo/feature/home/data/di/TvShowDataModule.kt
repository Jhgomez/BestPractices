package com.demo.feature.home.data.di

import com.demo.feature.home.data.api.TvShowService
import com.demo.feature.home.data.api.TvShowServiceImpl
import com.demo.feature.home.data.repository.TvShowRepoImpl
import com.demo.feature.home.domain.TvShowRepo

internal interface TvShowDataModule {
    fun bindsService(service: TvShowServiceImpl): TvShowService
    fun bindTvShowRepository(reop: TvShowRepoImpl): TvShowRepo
}