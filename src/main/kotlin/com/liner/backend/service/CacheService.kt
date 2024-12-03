package com.liner.backend.service

import com.liner.backend.client.RedisClient
import com.liner.backend.model.dto.HighLightCacheDto
import org.springframework.stereotype.Service

@Service
class CacheService(private val redisClient: RedisClient) {

    fun getHighlightCache(userId: String, pageSeq: Long) =
        redisClient.getCacheData("feed:${userId}:${pageSeq}", HighLightCacheDto::class.java)

}
