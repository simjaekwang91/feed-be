package com.liner.backend.entity

import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.data.jpa.domain.AbstractPersistable_.id

@Entity
@Table(name = "page_info")
class PageInfo (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "page_id")
    val pageId: Long = 0L,
    @Column(name = "user_id")
    var userId: String = "",
    @Embedded
    var auditInfo: AuditInfo = AuditInfo(),
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PageInfo) return false
        return pageId == other.pageId
    }

    override fun hashCode(): Int = pageId.hashCode()
}
