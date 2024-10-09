package com.overnightstay.data.repository

import com.overnightstay.data.api.UserApi
import com.overnightstay.data.dto.user.request.LoginRequest
import com.overnightstay.data.dto.user.request.RegisterRequest
import com.overnightstay.domain.irepository.IUserRepository
import com.overnightstay.domain.models.Token
import com.overnightstay.domain.models.User

class UserRepository(
    private val userApi: UserApi
) : IUserRepository {

    override suspend fun reg(user: User): Boolean {
        val x = userApi.reg(mapperUserToRegisterRequest(user))
        println("Result: $x")
//        if (x.isSuccess) storage.save(mapperUserToUserSM(user))
        return x.isSuccess
    }

    override suspend fun login(user: User): Result<Token> {
        val result = userApi.login(mapperUserTologinRequest(user))
        println("Result UserRepository -> login: $result")
        return result.map { Token(access = it.tokens?.access, refresh = it.tokens?.refresh) }
    }

    override suspend fun getPlayer(token: String): Boolean {
        val result = userApi.getPlayer("Bearer $token")
        println("Result: UserRepository -> player: $result")
        return true
    }

    fun mapperUserToRegisterRequest(user: User): RegisterRequest {
        return RegisterRequest(
            login = user.login,
            password = user.password,
//            email = user.email?:"", //пока email не обрабатывается - ошибка сервера 500
            email = "",
            phone = user.phone ?: "",
            first_name = user.first_name ?: "",
            last_name = user.last_name ?: ""
        )
    }

    fun mapperUserTologinRequest(user: User): LoginRequest {
        return LoginRequest(
            login = user.login,
            password = user.password
        )
    }

//    fun User.checkFields(): Boolean {
//        return this.email == null || this.phone == null || this.first_name == null || this.last_name == null
//    }
}