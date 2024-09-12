package com.overnightstay.view.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.overnightstay.domain.models.User
import com.overnightstay.domain.usecases.LoginUseCase
import kotlinx.coroutines.launch

class AuthViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    fun login(user: User) {
        println("MVVM login: ${user.login}")

        viewModelScope.launch {
            /*** На время разработки пропускаем пустую строку*/
            if (user.login.isEmpty()) {
//                _authState.emit(AuthState.Confirm(null))
            } else {
                val result = loginUseCase(user)
                println("MVVM result: $result")
//                if (result) _authState.emit(AuthState.Confirm(true))
//                else _authState.emit(AuthState.Auth(false))
            }
        }
    }

    class Factory(
        private val loginUseCase: LoginUseCase,

        ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
                return AuthViewModel(
                    loginUseCase = loginUseCase,
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}