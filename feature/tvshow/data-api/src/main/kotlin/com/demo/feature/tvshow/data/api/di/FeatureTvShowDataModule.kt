package com.demo.feature.tvshow.data.api.di

import com.demo.feature.tvshow.data.api.TvShowService
import com.demo.feature.tvshow.data.api.TvShowServiceImpl

interface FeatureTvShowDataModule {
    fun bindsService(service: TvShowServiceImpl): TvShowService
}