package com.liner.backend.repository

import com.liner.backend.entity.HighLightEntity
import org.springframework.data.jpa.repository.JpaRepository

interface HighlightRepository: JpaRepository<HighLightEntity, Long> {
    fun findTop3ByPageInfoPageIdOrderByAuditInfoCreatedAtDesc(pageId: Long): List<HighLightEntity>
}
