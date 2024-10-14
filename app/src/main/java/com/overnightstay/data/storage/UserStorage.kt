package com.overnightstay.data.storage

import android.content.SharedPreferences
import com.overnightstay.data.dto.user.storagemodel.UserSM
import com.overnightstay.domain.istorage.IUserStorage
import com.overnightstay.domain.models.User
import javax.inject.Inject

class UserStorage @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : IUserStorage {
    override fun get(): User {
        val userName = sharedPreferences.getString(USER_NAME, "")
        val first_name = sharedPreferences.getString(USER_FIRSTNAME, "")
        val last_nam = sharedPreferences.getString(USER_LASTNAME, "")
        val phone = sharedPreferences.getString(USER_PHONE, "")
        val email = sharedPreferences.getString(USER_EMAIL, "")
        val gender = sharedPreferences.getString(USER_EMAIL, "")
        val trainingCheck = sharedPreferences.getBoolean(USER_EMAIL, false)
        return User(
            userName = userName,
            first_name = first_name,
            last_name = last_nam,
            phone = phone,
            email = email,
            gender = gender,
            trainingCheck = trainingCheck
        )
    }

    override fun save(user: User) {
        sharedPreferences.edit().putString(USER_NAME, user.userName).apply()
        sharedPreferences.edit().putString(USER_FIRSTNAME, user.first_name).apply()
        sharedPreferences.edit().putString(USER_LASTNAME, user.last_name).apply()
        sharedPreferences.edit().putString(USER_PHONE, user.phone).apply()
        sharedPreferences.edit().putString(USER_EMAIL, user.email).apply()
        sharedPreferences.edit().putString(USER_GENDER, user.gender).apply()
        sharedPreferences.edit().putBoolean(TRAINONG_CHECK, user.trainingCheck).apply()
    }

//    fun mapperUserSMToUser(user: UserSM): User {
//        return User(
//            email = user.email ?: "",
//            phone = user.phone ?: "",
//            first_name = user.first_name ?: "",
//            last_name = user.last_name ?: ""
//        )
//    }

//    fun mapperUserToUserSM(user: User): UserSM {
//        return UserSM(
//            email = user.email ?: "",
//            phone = user.phone ?: "",
//            first_name = user.first_name ?: "",
//            last_name = user.last_name ?: ""
//        )
//    }

    companion object {
        private const val USER_NAME = "userName"
        private const val USER_FIRSTNAME = "userFirstName"
        private const val USER_LASTNAME = "userLastname"
        private const val USER_PHONE = "userPhone"
        private const val USER_EMAIL = "userEmail"
        private const val USER_GENDER = "userGender"
        private const val TRAINONG_CHECK = "trainingCheck"
    }
}
