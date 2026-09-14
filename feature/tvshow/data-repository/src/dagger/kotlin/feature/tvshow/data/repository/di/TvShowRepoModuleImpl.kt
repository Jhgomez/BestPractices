package feature.tvshow.data.repository.di

import com.demo.feature.tvshow.domain.TvShowRepo
import dagger.Binds
import dagger.Module
import feature.tvshow.data.repository.TvShowRepoImpl
import javax.inject.Singleton

@Module
interface TvShowRepoModuleImpl: TvShowRepoModule {

    @Singleton
    @Binds
    fun bindsTvShowRepo(repo: TvShowRepoImpl): TvShowRepo
}