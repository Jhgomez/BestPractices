package feature.tvshow.data.repository

import com.demo.core.domain.common.model.Page
import com.demo.feature.tvshow.data.api.TvShowService
import com.demo.feature.tvshow.domain.TvShow
import com.demo.feature.tvshow.domain.TvShowRepo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TvShowRepoImpl @Inject constructor(private val service: TvShowService): TvShowRepo {
    override suspend fun getTvShow(page: Int): Page<TvShow> {
        val response = service.getTvShows(page)

        return Page(
            page = response.page,
            results = response.results.map { dto ->
                TvShow(
                    adult = dto.adult,
                    backdropPath = dto.backdropPath,
                    id = dto.id,
                    name = dto.name,
                    originalLanguage = dto.originalLanguage,
                    originalName = dto.originalName,
                    overview = dto.overview,
                    posterPath = dto.posterPath,
                    mediaType = dto.mediaType,
                    genreIds = dto.genreIds,
                    popularity = dto.popularity,
                    firstAirDate = dto.firstAirDate,
                    voteAverage = dto.voteAverage,
                    voteCount = dto.voteCount,
                    originCountry = dto.originCountry
                )
            },
            totalPages = response.totalPages,
            totalResults = response.totalResults
        )
    }
}