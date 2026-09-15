package com.demo.feature.tvshow.presentation.di

import androidx.lifecycle.ViewModel
import com.demo.feature.tvshow.presentation.TvShowViewModel
import com.demo.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface TvShowPresentationModuleImpl: TvShowPresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(TvShowViewModel::class)
    override fun bindsTvShowViewModel(viewModel: TvShowViewModel): ViewModel
}

@Module(includes = [TvShowPresentationModuleImpl::class])
interface PublicTvShowPresentationModule
