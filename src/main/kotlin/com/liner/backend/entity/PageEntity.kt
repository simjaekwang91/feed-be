package com.liner.backend.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table(name = "page_info")
class PageEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "page_id")
    val pageId: Long = 0L,

    @Column(name = "url")
    val url: String = "",

    @Column(name = "title")
    val title: String = "",

    @Embedded
    var auditInfo: AuditInfo = AuditInfo(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val userInfo: UserEntity? = null,

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], mappedBy = "pageInfo")
    val highLightEntity: List<HighLightEntity>? = null,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "privacy_relationship_id")
    val privacyRelationship: PrivacyRelationshipEntity? = null,

    @OneToMany
    @JoinColumn(name = "privacy_user_mapping_id")
    val privacyUserMapping: List<PrivacyUserMappingEntity>? = null,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PageEntity) return false
        return pageId == other.pageId
    }

    override fun hashCode(): Int = pageId.hashCode()
}
