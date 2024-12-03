package com.liner.backend.service

import com.liner.backend.model.response.HighlightDto
import com.liner.backend.model.response.PageListDto
import com.liner.backend.repository.HighlightRepository
import com.liner.backend.repository.PageInfoRepository
import java.sql.Timestamp
import org.springframework.stereotype.Service

@Service
class FeedService(
    private val pageInfoRepository: PageInfoRepository,
    private val highlightRepository: HighlightRepository
) {
    fun getFeedList(userId: String, pageNo: Long, pageSize: Long): List<PageListDto>? {
         pageInfoRepository.getUserPageList(userId, pageSize, pageNo).also { pageInfoList ->
            //검색된 페이지 정보의 id를 모아서 한번에 in절로 highlight 검색
            val pageIdList = pageInfoList.map { it.pageId }
            val highlights = highlightRepository.findTop3HighlightsForPageIdList(pageIdList)
            return pageInfoList.map { pageInfo ->
                PageListDto(
                    url = pageInfo.url,
                    title = pageInfo.title,
                    userName = pageInfo.userInfo?.userName,
                    highlightList = highlights.filter { it.pageInfo?.pageId == pageInfo.pageId }
                        .map {
                            val content = it.content
                            val color = it.color
                            HighlightDto(content, color)
                        },
                    name = pageInfo.userInfo?.name,
                    createdAt = pageInfo.auditInfo.createdAt,
                )
            }
        }
    }
}
