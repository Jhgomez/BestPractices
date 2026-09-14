package com.demo.feature.tvshow.data.api.di

import com.demo.feature.tvshow.data.api.TvShowService
import com.demo.feature.tvshow.data.api.TvShowServiceImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface FeatureTvShowDataModuleImpl : FeatureTvShowDataModule {

    @Binds
    @Singleton
    override fun bindsService(service: TvShowServiceImpl): TvShowService
}