package com.liner.backend.controller

import com.liner.backend.model.response.FeedResponse
import com.liner.backend.model.response.PageListDto
import com.liner.backend.service.FeedService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/feed")
class FeedController(private val feedService: FeedService) {
    @Operation(summary = "Get Feed Page List")
    @GetMapping("/{userId}")
    fun getFeed(
        @PathVariable(value = "userId") userId: String,
        @RequestParam("pageNo") pageNo: Long,
        @RequestParam("pageSize") pageSize: Long,
    ):FeedResponse<List<PageListDto>?> = FeedResponse(data = feedService.getFeedList(userId, pageNo, pageSize))
}
