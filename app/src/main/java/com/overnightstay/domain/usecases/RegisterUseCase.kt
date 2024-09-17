package com.overnightstay.domain.usecases

import com.overnightstay.domain.irepository.IUserRepository
import com.overnightstay.domain.models.User

class RegisterUseCase(
    private val repository: IUserRepository,
    private val saveUserToStorage: SaveUserToSharedPrefUseCase
) {
    suspend operator fun invoke(user: User): Boolean {
        if (user.checkFields()) return false
        val result = repository.reg(user)
        if (result) saveUserToStorage(user)
        return result
    }

    fun User.checkFields(): Boolean {
        return this.email == null || this.phone == null || this.first_name == null || this.last_name == null
    }
}