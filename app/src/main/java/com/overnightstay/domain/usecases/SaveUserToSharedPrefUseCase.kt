package com.overnightstay.domain.usecases

import com.overnightstay.domain.istorage.IUserStorage
import com.overnightstay.domain.models.User

class SaveUserToSharedPrefUseCase(
    private val storage: IUserStorage
) {
    operator fun invoke(user: User) {
        storage.save(user)
    }
}