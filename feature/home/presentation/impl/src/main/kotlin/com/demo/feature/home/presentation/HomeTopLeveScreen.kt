package com.demo.feature.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.demo.core.feature.common.AppViewModelFactory
import com.demo.feature.home.domain.TvShow

@Composable
fun HomeTopLevelScreen(modifier: Modifier) {
    val vm = viewModel<TvShowViewModel>(factory = AppViewModelFactory.current)

    LaunchedEffect(null) {
        vm.getTvShows()
    }

    HomeTopLevelScreen(page = vm.movies.value)
}

@Composable
private fun HomeTopLevelScreen(page: List<TvShow>) {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items = page) { show ->
            Text(text = show.toString())
        }
    }

}
