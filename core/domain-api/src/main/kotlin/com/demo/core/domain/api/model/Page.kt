package com.demo.core.domain.api.model

data class Page<T>(
    val page: Int,
    val results: List<T>,
    val totalPages: Int,
    val totalResults: Int,
)