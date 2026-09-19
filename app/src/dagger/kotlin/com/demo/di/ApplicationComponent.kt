package com.demo.di

import com.demo.MainActivity
import com.demo.core.data.client.di.ClientModuleImpl
import com.demo.feature.home.data.di.PublicTvShowDataModule
import com.demo.feature.tvshow.com.demo.feature.home.presentation.di.PublicHomePresentationModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = [
    ClientModuleImpl::class,
    ViewModelBuilderModule::class,
    PublicTvShowDataModule::class,
    PublicHomePresentationModule::class
])
@Singleton
interface ApplicationComponent {
    fun inject(activity: MainActivity)
}