package com.liner.backend.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "user_info")
class UserEntity(
    @Id
    @Column(name = "user_id")
    val userId: String = "",

    @Column(name = "user_name")
    val userName: String = "",

    @Column(name = "name")
    val name: String = "",

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], mappedBy = "userInfo")
    val pageList: List<PageEntity>? = null,

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], mappedBy = "pageInfo")
    val privacyUserMappingEntity: List<PrivacyUserMappingEntity>? = null,

    @Embedded
    val auditInfo: AuditInfo? = null,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is UserEntity) return false
        return userId == other.userId
    }

    override fun hashCode(): Int = userId.hashCode()
}
