package com.overnightstay.domain.usecases

import com.overnightstay.data.repository.AuthRepository

class RePassUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(pass: String): Boolean {
        val result = repository.rePass(pass)
        return true
    }
}