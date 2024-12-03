package com.liner.backend.service

import com.liner.backend.model.response.HighlightDto
import com.liner.backend.model.response.PageListDto
import com.liner.backend.repository.HighlightRepository
import com.liner.backend.repository.PageInfoRepository
import java.sql.Timestamp
import java.time.Instant
import org.springframework.stereotype.Service

@Service
class FeedService(
    private val pageInfoRepository: PageInfoRepository,
    private val highlightRepository: HighlightRepository
) {
    fun getFeedList(userId: String, pageNo: Long, pageSize: Long): List<PageListDto>? {
        val result = pageInfoRepository.getUserPageList(userId, pageSize, pageNo)
        return result.map { row ->
            val data = row as Array<*>
            PageListDto(
                url = data[1] as String?,
                title = data[2] as String?,
                userName = data[5] as String?,
                highlightList = highlightRepository.findTop3ByPageInfoPageIdOrderByAuditInfoCreatedAtDesc(data[0] as Long).map {
                    HighlightDto(
                        it.content,
                        it.color
                    )
                },
                name = data[6] as String?,
                createdAt = (data[7] as Timestamp).toInstant()  // Timestamp -> Instant 변환
            )
        }
    }
}
