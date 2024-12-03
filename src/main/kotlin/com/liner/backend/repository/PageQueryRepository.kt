package com.liner.backend.repository

import com.liner.backend.entity.PageEntity

interface PageQueryRepository {
    fun getUserPageList(userId: String, pageSize: Long, offset: Long): List<PageEntity>
}
