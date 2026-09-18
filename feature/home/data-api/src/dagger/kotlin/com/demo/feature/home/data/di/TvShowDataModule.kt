package com.demo.feature.home.data.di

import com.demo.feature.home.data.api.TvShowService
import com.demo.feature.home.data.api.TvShowServiceImpl
import com.demo.feature.home.data.repository.TvShowRepoImpl
import com.demo.feature.home.domain.TvShowRepo
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
internal interface TvShowDataModuleImpl : TvShowDataModule {

    @Binds
    @Singleton
    override fun bindsService(service: TvShowServiceImpl): TvShowService

    @Binds
    @Singleton
    override fun bindTvShowRepository(reop: TvShowRepoImpl): TvShowRepo
}

@Module(includes = [TvShowDataModuleImpl::class])
interface PublicTvShowDataModule