package com.liner.backend.repository

import com.liner.backend.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<UserEntity, Long>, UserQueryRepository {
}
