package com.demo.feature.tvshow.data

import com.demo.feature.tvshow.data.api.TvShowService
import com.demo.feature.tvshow.data.api.TvShowServiceImpl
import com.demo.feature.tvshow.data.di.FeatureTvShowDataModule
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
internal interface FeatureTvShowDataModuleImpl : FeatureTvShowDataModule {

    @Binds
    @Singleton
    override fun bindsService(service: TvShowServiceImpl): TvShowService
}

@Module(includes = [FeatureTvShowDataModuleImpl::class])
interface PublicFeatureTvShowDataModule