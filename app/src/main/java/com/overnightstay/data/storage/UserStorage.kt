package com.overnightstay.data.storage

import android.content.SharedPreferences
import com.overnightstay.data.dto.user.storagemodel.UserSM
import com.overnightstay.domain.istorage.IUserStorage
import com.overnightstay.domain.models.User
import javax.inject.Inject

class UserStorage @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : IUserStorage{
    override fun get(): User {
        val first_name = sharedPreferences.getString(USER_NAME, "") ?: ""
        val last_nam = sharedPreferences.getString(USER_LASTNAME, "") ?: ""
        val phone = sharedPreferences.getString(USER_PHONE, "") ?: ""
        val email= sharedPreferences.getString(USER_EMAIL, "") ?: ""
        return mapperUserSMToUser(UserSM(first_name = first_name, last_name = last_nam, phone = phone, email = email))
    }

    override fun save(user: User) {
        sharedPreferences.edit().putString(USER_NAME, user.first_name).apply()
        sharedPreferences.edit().putString(USER_LASTNAME, user.last_name).apply()
        sharedPreferences.edit().putString(USER_PHONE, user.phone).apply()
        sharedPreferences.edit().putString(USER_EMAIL, user.email).apply()
    }

    private fun mapperUserSMToUser(user: UserSM): User {
        return User(
            login = "",
            password = "",
            email = user.email ?: "",
            phone = user.phone ?: "",
            first_name = user.first_name ?: "",
            last_name = user.last_name ?: ""
        )
    }

    companion object {
        private const val USER_NAME = "userName"
        private const val USER_LASTNAME = "userLastname"
        private const val USER_PHONE = "userPhone"
        private const val USER_EMAIL = "userEmail"
    }
}
