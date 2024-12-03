package com.liner.backend.repository.querydsl

import com.liner.backend.entity.PageEntity
import com.liner.backend.entity.PrivacyName
import com.liner.backend.entity.QPageEntity
import com.liner.backend.entity.QPrivacyUserMappingEntity
import com.liner.backend.entity.QUserEntity
import com.liner.backend.repository.PageQueryRepository
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.JPAExpressions
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
class PageQueryRepositoryImpl(private val queryFactory: JPAQueryFactory) : PageQueryRepository {




    /**
     * Get user page list
     *
     * public, mentioned, private 페이지를 검색하여 union
     * @param userId
     * @param pageSize
     * @param offset
     * @return
     */
    override fun getUserPageList(userId: String, pageSize: Long, offset: Long): List<PageEntity> {
            val page = QPageEntity.pageEntity
            val privacyUserMapping = QPrivacyUserMappingEntity.privacyUserMappingEntity
            val userInfo = QUserEntity.userEntity

            // Public pages (privacy_relationship_id = 1)
            val publicPages = queryFactory
                .selectFrom(page)
                .join(page.userInfo, userInfo)
                .where(page.privacyRelationship.privacyRelationshipId.eq(1)
                    .and(page.userInfo.userId.eq(userId)))
                .fetch()

            // Mentioned pages (privacy_relationship_id = 2 AND EXISTS condition)
            val mentionedPages = queryFactory
                .selectFrom(page)
                .join(page.userInfo, userInfo)
                .where(page.privacyRelationship.privacyRelationshipId.eq(2)
                    .and(JPAExpressions.selectOne()
                        .from(privacyUserMapping)
                        .where(privacyUserMapping.pageInfo.eq(page)
                            .and(privacyUserMapping.userInfo.userId.eq(userId)))
                        .exists()))
                .fetch()

            // Private pages (privacy_relationship_id = 3 AND user_info.userId = userId)
            val privatePages = queryFactory
                .selectFrom(page)
                .join(page.userInfo, userInfo)
                .where(page.privacyRelationship.privacyRelationshipId.eq(3)
                    .and(page.userInfo.userId.eq(userId)))
                .fetch()

            // Combine results (already filtered by privacy relationship)
            val allPages = (publicPages + mentionedPages + privatePages)

            // Return only the required pages with sorting and pagination
            return allPages
                .sortedByDescending { it.auditInfo.createdAt }
                .take(pageSize.toInt())
                .drop(offset.toInt())
        }
}
