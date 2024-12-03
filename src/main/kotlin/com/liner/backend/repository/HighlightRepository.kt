package com.liner.backend.repository

import com.liner.backend.entity.HighLightEntity
import io.lettuce.core.dynamic.annotation.Param
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface HighlightRepository: JpaRepository<HighLightEntity, Long> {
    fun findTop3ByPageInfoPageIdOrderByAuditInfoCreatedAtDesc(pageId: Long): List<HighLightEntity>

    @Query(
        value = """
            SELECT h.highlight_id,
                   h.color,
                   h.content,
                   h.page_id,
                   FROM_UNIXTIME(UNIX_TIMESTAMP(h.created_at)) AS created_at,
               FROM_UNIXTIME(UNIX_TIMESTAMP(h.updated_at)) AS updated_at
            FROM highlight h
            JOIN (
                SELECT h.highlight_id, h.page_id, h.created_at,
                       ROW_NUMBER() OVER (PARTITION BY h.page_id ORDER BY h.created_at DESC) AS row_num
                FROM highlight h
                WHERE h.page_id IN :pageIdList
            ) AS ranked_h ON h.highlight_id = ranked_h.highlight_id
            WHERE ranked_h.row_num <= 3
            ORDER BY h.page_id, h.created_at DESC
        """,
        nativeQuery = true
    )
    fun findTop3HighlightsForPageIdList(
        @Param("pageIdList") pageIdList: List<Long>
    ): List<HighLightEntity>

}
