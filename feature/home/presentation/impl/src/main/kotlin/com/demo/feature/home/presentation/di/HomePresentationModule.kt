package com.demo.feature.home.presentation.di

import androidx.lifecycle.ViewModel
import com.demo.feature.home.presentation.toplevel.HomeTopLevelViewModel

internal interface HomePresentationModule {
    fun bindsHomeTopLevelViewModel(viewModel: HomeTopLevelViewModel): ViewModel
}