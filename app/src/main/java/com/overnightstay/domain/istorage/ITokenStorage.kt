package com.overnightstay.domain.istorage

import com.overnightstay.domain.models.Token

interface ITokenStorage {
    fun getAccessToken(): String
    fun getRefreshToken(): String
    fun saveAll(token: Token)
    fun saveAccessToken(token: String)
    fun saveRefreshToken(token: String)
}
