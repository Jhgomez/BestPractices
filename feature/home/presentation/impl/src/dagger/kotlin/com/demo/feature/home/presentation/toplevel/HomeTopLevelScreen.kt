package com.demo.feature.home.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.demo.core.navigation.viewmodel.AppViewModelFactory
import com.demo.feature.home.presentation.toplevel.HomeTopLevelScreen
import com.demo.feature.home.presentation.toplevel.HomeTopLevelViewModel

@Composable
fun HomeTopLevelScreen(modifier: Modifier) {
    val vm = viewModel<HomeTopLevelViewModel>(factory = AppViewModelFactory.current)

    LaunchedEffect(null) {
        vm.getTvShows()
    }

    HomeTopLevelScreen(page = vm.movies.value)
}

