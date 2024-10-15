package com.overnightstay.view.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.overnightstay.domain.AppState
import com.overnightstay.domain.models.User
import com.overnightstay.domain.usecases.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

    private val _appState = MutableStateFlow<AppState>(AppState.None)
    val appState: StateFlow<AppState> = _appState

    fun login(user: User) {
        println("AuthViewModel login: ${user.login}")

        viewModelScope.launch {

            /*** На время разработки пропускаем пустую строку*/
            if (user.login?.isEmpty() == true) {
                _appState.emit(AppState.Success(Pair(true, null)))
            } else {

                _appState.emit(AppState.Loading)
                val result = loginUseCase(user)
                println("AuthViewModel result: $result")
                _appState.emit(AppState.Success(Pair(result.first, result.second?.gender)))
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