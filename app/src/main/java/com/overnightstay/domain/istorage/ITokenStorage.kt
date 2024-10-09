package com.overnightstay.domain.istorage

interface ITokenStorage {
    fun getAccessToken(): String
    fun getRefreshToken(): String
    fun saveAll(tokens: Pair<String, String>)
    fun saveAccessToken(token: String)
    fun saveRefreshToken(token: String)
}
