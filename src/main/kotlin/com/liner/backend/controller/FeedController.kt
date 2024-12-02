package com.liner.backend.controller

import com.liner.backend.dto.response.FeedResponse
import com.liner.backend.service.FeedService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/feed")
class FeedController(private val feedService: FeedService) {
    @Operation(summary = "Get Feed Page List")
    @GetMapping("/{id}")
    fun getFeed(
        @PathVariable(value = "id") page: Long,
    ):FeedResponse<String> = FeedResponse(data = feedService.getFeedList())
}
