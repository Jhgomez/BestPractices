package feature.tvshow.data.repository.di

import com.demo.feature.tvshow.domain.TvShowRepo
import feature.tvshow.data.repository.TvShowRepoImpl

interface TvShowRepoModule {
    fun bindTvShowRepository(reop: TvShowRepoImpl): TvShowRepo
}