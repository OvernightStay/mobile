package com.overnightstay.domain.usecases

import com.overnightstay.domain.irepository.IUserRepository
import com.overnightstay.domain.istorage.ITokenStorage
import com.overnightstay.domain.models.User

class LoginUseCase(
    private val repository: IUserRepository,
    private val storage: ITokenStorage,
    private val getPlayerFromApiUseCase: GetPlayerFromApiUseCase
) {
    suspend operator fun invoke(user: User): Boolean {
        val result = repository.login(user)
        if (result.isSuccess) {
            val tokens = result.getOrNull()
            tokens?.let {
                storage.saveAll(tokens)
                tokens.access?.let { access -> getPlayerFromApiUseCase(access) }
            }
        }
        return result.isSuccess
    }
}