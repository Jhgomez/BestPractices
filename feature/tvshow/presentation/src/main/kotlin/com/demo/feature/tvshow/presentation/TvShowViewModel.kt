package com.demo.feature.tvshow.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.feature.tvshow.domain.TvShow
import com.demo.feature.tvshow.domain.TvShowRepo
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class TvShowViewModel @Inject constructor(private val repo: TvShowRepo): ViewModel() {
    val movies = mutableStateOf(emptyList<TvShow>())

    fun getTvShows() {
        viewModelScope.launch {
            movies.value = repo.getTvShow(1).results
        }
    }
}