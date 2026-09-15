package com.demo.feature.tvshow.data.di

import com.demo.feature.tvshow.data.api.TvShowService
import com.demo.feature.tvshow.data.api.TvShowServiceImpl
import com.demo.feature.tvshow.data.repository.TvShowRepoImpl
import com.demo.feature.tvshow.domain.TvShowRepo

internal interface FeatureTvShowDataModule {
    fun bindsService(service: TvShowServiceImpl): TvShowService
    fun bindTvShowRepository(reop: TvShowRepoImpl): TvShowRepo
}