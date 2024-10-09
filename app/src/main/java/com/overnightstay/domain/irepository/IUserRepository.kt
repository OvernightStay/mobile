package com.overnightstay.domain.irepository

import com.overnightstay.domain.models.Token
import com.overnightstay.domain.models.User

interface IUserRepository {
    suspend fun reg(user: User): Boolean
    suspend fun login(user: User): Result<Token>
    suspend fun getPlayer(accessToken: String): Boolean
}