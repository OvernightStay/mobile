package com.overnightstay.view.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.overnightstay.domain.AppState
import com.overnightstay.domain.models.User
import com.overnightstay.domain.usecases.GetPlayerFromApiUseCase
import com.overnightstay.domain.usecases.LoginUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val loginUseCase: LoginUseCase,
    private val getPlayerFromApiUseCase: GetPlayerFromApiUseCase
) : ViewModel() {

    private var _isEntry = MutableSharedFlow<Pair<Boolean, String?>>()
    val isEntry: SharedFlow<Pair<Boolean, String?>>
        get() = _isEntry.asSharedFlow()

    private val _appState = MutableStateFlow<AppState>(AppState.None)
    val appState: StateFlow<AppState> = _appState

    fun login(user: User) {
        println("MVVM login: ${user.login}")

        viewModelScope.launch {

            _appState.emit(AppState.Loading)

            /*** На время разработки пропускаем пустую строку*/
            if (user.login?.isEmpty() == true) {
                _appState.emit(AppState.Success(Pair(true, null)))
//                _isEntry.emit(Pair(true, null))
            } else {
                val result = loginUseCase(user)
                println("MVVM result: $result")
                _appState.emit(AppState.Success(Pair(result.first, result.second?.gender)))
//                _isEntry.emit(Pair(result.first, result.second?.gender))
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