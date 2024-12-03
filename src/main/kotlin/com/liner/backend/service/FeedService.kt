package com.liner.backend.service

import com.liner.backend.model.response.PageListDto
import com.liner.backend.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class FeedService(private val userRepository: UserRepository) {
    fun getFeedList(): PageListDto {
        userRepository.findAll().also {
            return PageListDto("")
        }
    }
}
