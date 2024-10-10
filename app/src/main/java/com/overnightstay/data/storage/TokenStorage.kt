package com.overnightstay.data.storage

import android.content.SharedPreferences
import com.overnightstay.domain.istorage.ITokenStorage
import com.overnightstay.domain.models.Token
import javax.inject.Inject

class TokenStorage @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ITokenStorage {
    override fun getAccessToken(): String {
        return sharedPreferences.getString(ACCESSTOKEN, "") ?: ""
    }

    override fun getRefreshToken(): String {
        return sharedPreferences.getString(REFRESHTOKEN, "") ?: ""
    }

    override fun saveAll(token: Token) {
        sharedPreferences.edit().putString(ACCESSTOKEN, token.access).apply()
        sharedPreferences.edit().putString(REFRESHTOKEN, token.refresh).apply()
    }

    override fun saveAccessToken(token: String) {
        sharedPreferences.edit().putString(ACCESSTOKEN, token).apply()
    }

    override fun saveRefreshToken(token: String) {
        sharedPreferences.edit().putString(ACCESSTOKEN, token).apply()
    }

    companion object {
        private const val ACCESSTOKEN = "accessToken"
        private const val REFRESHTOKEN = "refreshToken"
    }
}
