package com.overnightstay.domain.usecases

import com.overnightstay.domain.irepository.IUserRepository
import com.overnightstay.domain.istorage.ITokenStorage

class GetPlayerFromApiUseCase(
    private val repository: IUserRepository,
    private val storage: ITokenStorage,
    ) {
    suspend operator fun invoke(tokenAccess: String): Boolean {
        return getPlayer(tokenAccess)
    }

    suspend operator fun invoke(): Boolean {
        val token = storage.getAccessToken()
        return getPlayer(token)
    }

    private suspend fun getPlayer(token: String): Boolean {
        if (token.isNotEmpty()) {
            val result = repository.getPlayer(token)
            return result
        }
        return false
    }
}