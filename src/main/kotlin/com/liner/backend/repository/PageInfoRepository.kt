package com.liner.backend.repository

import com.liner.backend.entity.PageEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PageInfoRepository: JpaRepository<PageEntity, Long> {
}
