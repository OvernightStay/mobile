package com.overnightstay.domain.usecases

import com.overnightstay.domain.irepository.IUserRepository
import com.overnightstay.domain.istorage.ITokenStorage
import com.overnightstay.domain.istorage.IUserStorage

class UpdatePlayerOnApiUseCase(
    private val repository: IUserRepository,
    private val tokenStorage: ITokenStorage,
    private val userStorage: IUserStorage,
) {
    suspend operator fun invoke(userName: String, gender: String): Boolean {
        val token = tokenStorage.getAccessToken()

        if (token.isNotEmpty()) {
            val result = repository.updatePlayer(token = token, userName = userName, gender = gender)
            if (result.isSuccess) {
                val user = result.getOrNull()
                user?.let {
                    userStorage.save(user)
                }
                return result.isSuccess
            }
        }
        return false
    }
}