package com.liner.backend.model.dto

import java.time.Instant

data class HighLightCacheDto (
    val highLightId: Long,
    val highLightText: String,
    val createdAt: Instant,
)
