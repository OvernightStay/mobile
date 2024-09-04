package com.overnightstay.domain.usecases

import com.overnightstay.data.repository.AuthRepository

class ConfirmCodeUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(code: String): Boolean {
        val result = repository.confirmCode(code)
        return true
    }
}