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
        val pageInfoList = pageInfoRepository.getUserPageList(userId, pageSize, pageNo)
        //검색된 페이지 정보의 id를 모아서 한번에 in절로 highlight 검색
        val pageIdList = pageInfoList.map { (it as Array<*>)[0] as Long }
        val highlights = highlightRepository.findTop3HighlightsForPageIdList(pageIdList)

        return pageInfoList.map { row ->
            val data = row as Array<*>
            PageListDto(
                url = data[1] as String?,
                title = data[2] as String?,
                userName = data[5] as String?,
                highlightList = highlights.filter { (it as Array<*>)[4] == data[0] as Long }
                    .map {
                        val content = (it as Array<*>)[3] as String
                        val color = it[2] as String
                        HighlightDto(content, color)
                    },
                name = data[6] as String?,
                createdAt = (data[7] as Timestamp).toInstant()
            )
        }
    }
}
