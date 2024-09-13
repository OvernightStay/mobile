package com.overnightstay.domain.usecases

import com.overnightstay.domain.irepository.IUserRepository
import com.overnightstay.domain.models.User

class LoginUseCase(
    private val repository: IUserRepository
) {
    suspend operator fun invoke(user: User): Boolean {
        val result = repository.login(user)
        return result
    }
}