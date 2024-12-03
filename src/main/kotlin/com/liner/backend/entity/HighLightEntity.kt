package com.liner.backend.entity

import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "highlight")
class HighLightEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "highlight_id")
    val highLightId: Long = 0L,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "page_id")
    val pageInfo: PageEntity? = null,


    @Column(name = "content")
    val content: String = "",

    @Column(name = "color")
    val color: String = "",

    @Embedded
    val auditInfo: AuditInfo? = null,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is HighLightEntity) return false
        return highLightId == other.highLightId
    }

    override fun hashCode(): Int = highLightId.hashCode()
}
