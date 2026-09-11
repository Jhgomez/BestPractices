package com.demo.core.domain.common.model

data class Page<T>(
    val page: Int,
    val results: List<T>,
    val totalPages: Int,
    val totalResults: Int,
)