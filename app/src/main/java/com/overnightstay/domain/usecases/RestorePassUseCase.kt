package com.overnightstay.domain.usecases

import com.overnightstay.data.repository.AuthRepository

class RestorePassUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(userName: String, login: String): Boolean {
        val result = repository.reg(userName, login)
        return true
    }
}