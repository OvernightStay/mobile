package com.overnightstay.data.storage

import android.content.SharedPreferences
import com.overnightstay.domain.istorage.ITokenStorage
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

    override fun saveAll(tokens: Pair<String, String>) {
        sharedPreferences.edit().putString(ACCESSTOKEN, tokens.first).apply()
        sharedPreferences.edit().putString(REFRESHTOKEN, tokens.second).apply()
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
