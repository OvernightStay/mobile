package com.overnightstay.domain.usecases

import com.overnightstay.data.repository.AuthRepository

class GetCodeUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String): Boolean {
        val result = repository.getCode(email)
        return true
    }
}