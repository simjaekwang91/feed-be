package com.liner.backend.model.response

import java.time.Instant

data class PageListDto (
    val userName: String?,
    val name: String?,
    val url: String?,
    val title: String?,
    val highlightList: List<HighlightDto>? = null,
    val createdAt: Instant,
)

data class HighlightDto(
    val content: String,
    val color: String,
)
