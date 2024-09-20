package com.overnightstay.domain.irepository

import com.overnightstay.domain.models.User

interface IUserRepository {
    suspend fun reg(user: User): Boolean
    suspend fun login(user: User): Boolean
}