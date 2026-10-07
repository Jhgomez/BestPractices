package com.demo.di

import android.content.Context
import com.demo.MainActivity
import com.demo.core.data.client.impl.di.ClientModuleImpl
import com.demo.feature.home.data.di.PublicTvShowDataModule
import com.demo.feature.tvshow.com.demo.feature.home.presentation.di.PublicHomePresentationModule
import dagger.BindsInstance
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

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance @AppContext applicationContext: Context): ApplicationComponent
    }
}