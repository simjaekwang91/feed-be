package com.liner.backend.entity

import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "page_info")
class PageInfo (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "page_id")
    val pageId: Long? = null,
    @Column(name = "user_id")
    val userId: String,
    @Embedded
    val auditInfo: AuditInfo
)
