package com.overnightstay.domain.usecases

import com.overnightstay.domain.irepository.IUserRepository
import com.overnightstay.domain.istorage.ITokenStorage
import com.overnightstay.domain.models.User

class LoginUseCase(
    private val repository: IUserRepository,
    private val storage: ITokenStorage,
    private val getPlayerFromApiUseCase: GetPlayerFromApiUseCase
) {
    suspend operator fun invoke(user: User): Pair<Boolean, User?> {
        var pairUser: User? = null
        val result = repository.login(user)
        if (result.isSuccess) {
            val tokens = result.getOrNull()
            tokens?.let {
                //Сохраняем полученные токены в Pref, нам они понядобяться на каждом запросе к API
                storage.saveAll(tokens)
                //Если есть токен access и он валидный, то сразу и получаем информацию об игроке
                //чтобы пропустить или показать фрагмент с выбором пола персонажа
                //ну и записать в Pref данные об игроки для отображении на экранах - будем делать это
                //в getPlayerFromApiUseCase
                val pair = tokens.access?.let { access -> getPlayerFromApiUseCase(access) }
                if (pair != null) {
                    pairUser = pair.second!!
                }
            }
        }
        return Pair(result.isSuccess, pairUser)
    }
}