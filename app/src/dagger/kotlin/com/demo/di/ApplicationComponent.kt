package com.demo.di

import com.demo.MainActivity
import com.demo.core.data.client.di.ClientModuleImpl
import com.demo.feature.tvshow.data.api.di.FeatureTvShowDataModuleImpl
import dagger.Component
import feature.tvshow.data.repository.di.TvShowRepoModuleImpl
import javax.inject.Singleton

@Component(modules = [
    ClientModuleImpl::class,
    FeatureTvShowDataModuleImpl::class,
    TvShowRepoModuleImpl::class,
    ViewModelBuilderModule::class
])
@Singleton
interface ApplicationComponent {
    fun inject(activity: MainActivity)
}