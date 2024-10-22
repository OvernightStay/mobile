package com.overnightstay.domain.usecases

import com.overnightstay.domain.irepository.IUserRepository
import com.overnightstay.domain.istorage.ITokenStorage
import com.overnightstay.domain.istorage.IUserStorage
import com.overnightstay.domain.models.User

class GetPlayerFromApiUseCase(
    private val repository: IUserRepository,
    private val tokenStorage: ITokenStorage,
    private val userStorage: IUserStorage,
) {
    suspend operator fun invoke(tokenAccess: String): Pair<Boolean, User?> {
        return getPlayer(tokenAccess)
    }

    suspend operator fun invoke(): Pair<Boolean, User?> {
        val token = tokenStorage.getAccessToken()
        return getPlayer(token)
    }

    //получили игрока с сервера и сохранили его в pref
    private suspend fun getPlayer(token: String): Pair<Boolean, User?> {
        if (token.isNotEmpty()) {
            val result = repository.getPlayer(token)
            if (result.isSuccess) {
                val user = result.getOrNull()
                user?.let {
                    userStorage.save(user)
                }
                return Pair(result.isSuccess, user)
            }
        }
        return Pair(false, null)
    }
}