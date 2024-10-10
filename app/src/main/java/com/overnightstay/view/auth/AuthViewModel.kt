package com.overnightstay.view.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.overnightstay.domain.models.User
import com.overnightstay.domain.usecases.GetPlayerFromApiUseCase
import com.overnightstay.domain.usecases.LoginUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val loginUseCase: LoginUseCase,
    private val getPlayerFromApiUseCase: GetPlayerFromApiUseCase
) : ViewModel() {

    private var _isEntry = MutableSharedFlow<Pair<Boolean, String?>>()
    val isEntry: SharedFlow<Pair<Boolean, String?>>
        get() = _isEntry.asSharedFlow()

    fun login(user: User) {
        println("MVVM login: ${user.login}")

        viewModelScope.launch {
            /*** На время разработки пропускаем пустую строку*/
            if (user.login?.isEmpty() == true) {
                _isEntry.emit(Pair(true, null))
            } else {
                val result = loginUseCase(user)
                println("MVVM result: $result")
                _isEntry.emit(Pair(result.first, result.second?.gender))
            }
        }
    }

    class Factory(
        private val loginUseCase: LoginUseCase,
        private val getPlayerFromApiUseCase: GetPlayerFromApiUseCase
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
                return AuthViewModel(
                    loginUseCase = loginUseCase,
                    getPlayerFromApiUseCase = getPlayerFromApiUseCase

                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}