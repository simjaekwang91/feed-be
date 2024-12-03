package com.liner.backend.service

import com.liner.backend.exception.BusinessErrorType
import com.liner.backend.exception.BusinessRuleException
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
        if (userId.isBlank()) throw BusinessRuleException(BusinessErrorType.NOT_BLANK_USER_ID)

        pageInfoRepository.getUserPageList(userId, pageSize, pageNo).also { pageInfoList ->
            if(pageInfoList.isEmpty()) throw BusinessRuleException(BusinessErrorType.NOT_EMPTY_PAGE_LIST)

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
