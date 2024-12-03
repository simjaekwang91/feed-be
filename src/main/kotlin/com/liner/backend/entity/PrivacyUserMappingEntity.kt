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
@Table(name = "privacy_user_mapping")
class PrivacyUserMappingEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "access_id")
    val accessId: Long = 0L,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "page_id")
    val pageInfo: PageEntity? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val userInfo: UserEntity? = null,

    @Embedded
    val auditInfo: AuditInfo? = null

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PrivacyUserMappingEntity) return false
        return accessId == other.accessId
    }

    override fun hashCode(): Int = accessId.hashCode()
}
