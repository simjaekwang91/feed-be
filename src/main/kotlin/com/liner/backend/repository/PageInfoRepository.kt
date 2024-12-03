package com.liner.backend.repository

import com.liner.backend.entity.HighLightEntity
import com.liner.backend.entity.PageEntity
import io.lettuce.core.dynamic.annotation.Param
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PageInfoRepository: JpaRepository<PageEntity, Long> {

    @Query("""
        SELECT p.page_id, p.url, p.title, p.privacy_relationship_id, ui.user_id, ui.user_name, ui.name , 
               FROM_UNIXTIME(UNIX_TIMESTAMP(p.created_at)) AS created_at,
               FROM_UNIXTIME(UNIX_TIMESTAMP(p.updated_at)) AS updated_at
        FROM page_info p
        JOIN user_info ui ON p.user_id = ui.user_id
        WHERE p.privacy_relationship_id = 1
        
        UNION ALL
        
        SELECT p.page_id, p.url, p.title, p.privacy_relationship_id, ui.user_id, ui.user_name, ui.name ,
         FROM_UNIXTIME(UNIX_TIMESTAMP(p.created_at)) AS created_at,
               FROM_UNIXTIME(UNIX_TIMESTAMP(p.updated_at)) AS updated_at
        FROM page_info p
        JOIN user_info ui ON p.user_id = ui.user_id
        WHERE p.privacy_relationship_id = 2
        AND EXISTS (
            SELECT 1
            FROM privacy_user_mapping pum
            WHERE pum.page_id = p.page_id
            AND pum.user_id = :userId
        )
        
        UNION ALL
        
        SELECT p.page_id, p.url, p.title, p.privacy_relationship_id, ui.user_id, ui.user_name, ui.name,
         FROM_UNIXTIME(UNIX_TIMESTAMP(p.created_at)) AS created_at,
               FROM_UNIXTIME(UNIX_TIMESTAMP(p.updated_at)) AS updated_at
        FROM page_info p
        JOIN user_info ui ON p.user_id = ui.user_id
        WHERE p.privacy_relationship_id = 3
        AND ui.user_id = :userId
        
        ORDER BY created_at DESC
        LIMIT :pageSize OFFSET :offset
    """, nativeQuery = true)
    fun getUserPageList(
        @Param("userId") userId: String,
        @Param("pageSize") pageSize: Long,
        @Param("offset") offset: Long
    ): List<PageEntity>


}
