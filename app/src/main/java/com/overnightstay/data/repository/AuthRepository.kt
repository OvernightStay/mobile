package com.overnightstay.data.repository

import com.overnightstay.domain.irepository.IAuthRepository

class AuthRepository() : IAuthRepository {

    override suspend fun reg(userName: String, login: String): Boolean {
//        val x = authApi.reg(UserLoginRequest(userName, login))
//        println("Result: $x")
        return true
    }

    override suspend fun login(login: String): Boolean {
//        val x = authApi.login(LoginRequest(login))
//        println("Result: $x")
        return true
    }

    override suspend fun verify(code: String): Boolean {
//        val x = authApi.verify(CodeRequest(code))
//        println("Result: $x")
        return true
    }

    override suspend fun getCode(email: String): Boolean {
        return true
    }

    override suspend fun confirmCode(code: String): Any {
        return true
    }

    override suspend fun rePass(pass: String): Any {
        return true
    }
}