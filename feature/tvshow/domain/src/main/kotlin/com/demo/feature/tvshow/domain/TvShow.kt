package com.demo.feature.tvshow.domain

data class TvShow(
    val adult: Boolean,
    val backdropPath: String?,
    val id: Int,
    val name: String,
    val originalLanguage: String,
    val originalName: String,
    val overview: String,
    val posterPath: String?,
    val mediaType: String,
    val genreIds: List<Int>,
    val popularity: Double,
    val firstAirDate: String,
    val voteAverage: Double,
    val voteCount: Int,
    val originCountry: List<String>
)
