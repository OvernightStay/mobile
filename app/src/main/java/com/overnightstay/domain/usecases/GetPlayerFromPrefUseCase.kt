package com.overnightstay.domain.usecases

import com.overnightstay.domain.istorage.IUserStorage
import com.overnightstay.domain.models.User

class GetPlayerFromPrefUseCase(
    private val userStorage: IUserStorage,
) {
    operator fun invoke(): User {
        return userStorage.get()
    }
}