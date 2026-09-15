package com.demo.di

import com.demo.MainActivity
import com.demo.core.data.client.di.ClientModuleImpl
import com.demo.feature.tvshow.data.PublicFeatureTvShowDataModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = [
    ClientModuleImpl::class,
    PublicFeatureTvShowDataModule::class,
    ViewModelBuilderModule::class
])
@Singleton
interface ApplicationComponent {
    fun inject(activity: MainActivity)
}