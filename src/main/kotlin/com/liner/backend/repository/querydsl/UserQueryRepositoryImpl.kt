package com.liner.backend.repository.querydsl

import com.liner.backend.repository.UserQueryRepository
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class UserQueryRepositoryImpl(private val queryFactory: JPAQueryFactory): UserQueryRepository {

    fun getUserPageList(){

    }
}
