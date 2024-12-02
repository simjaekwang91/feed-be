package com.liner.backend.dto.response

data class FeedResponse<T>(
    val message: String? = null,
    val data: T
)
