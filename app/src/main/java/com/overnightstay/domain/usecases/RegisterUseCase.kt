package com.overnightstay.domain.usecases

import com.overnightstay.domain.irepository.IUserRepository
import com.overnightstay.domain.models.User

class RegisterUseCase(
    private val repository: IUserRepository
) {
    suspend operator fun invoke(user: User): Boolean {
        val result = repository.reg(user)
        return true
    }
}