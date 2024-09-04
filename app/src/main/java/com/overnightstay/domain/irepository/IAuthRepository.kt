package com.overnightstay.domain.irepository

interface IAuthRepository {
    suspend fun reg(userName: String, login: String): Boolean
    suspend fun login(login: String): Boolean
    suspend fun verify(code: String): Boolean
    suspend fun getCode(email: String): Boolean
    suspend fun confirmCode(code: String): Any
    suspend fun rePass(pass: String): Any
}