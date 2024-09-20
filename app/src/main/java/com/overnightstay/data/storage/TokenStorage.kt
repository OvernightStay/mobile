package com.overnightstay.data.storage

import android.content.SharedPreferences
import com.overnightstay.domain.istorage.ITokenStorage
import com.overnightstay.domain.models.Tokens
import javax.inject.Inject

class TokenStorage @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ITokenStorage{
    override fun get(): Tokens {
        val refresh = sharedPreferences.getString(REFRESH, "") ?: ""
        val access = sharedPreferences.getString(ACCESS, "") ?: ""
        return Tokens(refresh = refresh, access = access)
    }

    override fun save(tokens: Tokens) {
        sharedPreferences.edit().putString(REFRESH, tokens.refresh).apply()
        sharedPreferences.edit().putString(ACCESS, tokens.access).apply()
    }

    companion object {
        private const val REFRESH = "refresh"
        private const val ACCESS = "access"
    }
}
