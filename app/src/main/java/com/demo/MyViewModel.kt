package com.demo

import androidx.lifecycle.ViewModel
import com.demo.feature.tvshow.domain.TvShowRepo
import javax.inject.Inject

class MyViewModel @Inject constructor(private val repo: TvShowRepo): ViewModel() {
}