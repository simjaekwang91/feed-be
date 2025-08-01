package com.liner.backend.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "privacy_info")
class PrivacyRelationshipEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "privacy_id")
    val privacyRelationshipId: Long = 0L,

    @Enumerated(EnumType.STRING)
    @Column(name = "relationship_name")
    val relationshipName: PrivacyName = PrivacyName.PUBLIC,

    @Embedded
    val auditInfo: AuditInfo? = null,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PrivacyRelationshipEntity) return false
        return privacyRelationshipId == other.privacyRelationshipId
    }

    override fun hashCode(): Int = privacyRelationshipId.hashCode()
}

enum class PrivacyName(val code: Int) {
    PUBLIC(1),   // 전체공개
    MENTHIONED(2), // 지정된 사용자 공개
    PRIVATE(3); // 비공개

    companion object {
        fun fromCode(code: Int) = entries.first { it.code == code }
    }
}
