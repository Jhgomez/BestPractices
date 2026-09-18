package com.demo.feature.home.presentation.di

import androidx.lifecycle.ViewModel
import com.demo.feature.tvshow.presentation.TvShowViewModel

internal interface TvShowPresentationModule {
    fun bindsTvShowViewModel(viewModel: TvShowViewModel): ViewModel
}