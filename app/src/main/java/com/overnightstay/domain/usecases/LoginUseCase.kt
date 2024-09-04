package com.overnightstay.domain.usecases

import com.overnightstay.data.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class LoginUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(login: String): Boolean {
        val result = repository.login(login)
        return true
    }
}