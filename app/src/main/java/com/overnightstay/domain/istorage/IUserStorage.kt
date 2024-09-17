package com.overnightstay.domain.istorage

import com.overnightstay.domain.models.User

interface IUserStorage {
//    fun get(): User
    fun save(user: User)
}
