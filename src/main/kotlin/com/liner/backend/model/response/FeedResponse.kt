package com.liner.backend.model.response

data class FeedResponse<T>(
    val message: String? = null,
    val data: T
)
