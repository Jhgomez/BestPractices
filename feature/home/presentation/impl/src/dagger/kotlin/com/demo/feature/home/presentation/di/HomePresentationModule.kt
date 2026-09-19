package com.demo.feature.tvshow.com.demo.feature.home.presentation.di

import androidx.lifecycle.ViewModel
import com.demo.feature.home.presentation.toplevel.HomeTopLevelViewModel
import com.demo.di.ViewModelKey
import com.demo.feature.home.presentation.di.HomePresentationModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface HomePresentationModuleImpl: HomePresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeTopLevelViewModel::class)
    override fun bindsHomeTopLevelViewModel(viewModel: HomeTopLevelViewModel): ViewModel
}

@Module(includes = [HomePresentationModuleImpl::class])
interface PublicHomePresentationModule
