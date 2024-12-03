package com.liner.backend.client

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.liner.backend.exception.RedisErrorType
import com.liner.backend.exception.RedisException
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component

@Component
class RedisClient(private val redisTemplate: RedisTemplate<String, String>) {
    private val mapper: ObjectMapper = jacksonObjectMapper()
            .registerModule(JavaTimeModule())
            .findAndRegisterModules()

    fun <T> setCacheData(key: String, data: T) {
        validateKey(key)
        return redisTemplate.opsForValue().set(key, mapper.writeValueAsString(data))

    }

    fun <T> getCacheData(key: String, clazz: Class<T>): T? {
        validateKey(key)
        return redisTemplate.opsForValue().get(key)?.let {
            mapper.readValue(it, clazz)
        }
    }

    private fun validateKey(key: String) {
        require(key.isNotBlank()) { throw RedisException(RedisErrorType.KEY_IS_NOT_EMPTY) }
    }
}
