package com.overnightstay.view.reg

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.overnightstay.domain.AppState
import com.overnightstay.domain.models.User
import com.overnightstay.domain.usecases.RegisterUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class RegViewModel(private val registerUseCase: RegisterUseCase) : ViewModel() {
    private var _isEntry = MutableSharedFlow<Triple<Boolean, String, String>>()
    val isEntry: SharedFlow<Triple<Boolean, String, String>>
        get() = _isEntry.asSharedFlow()

    private val _appState = MutableStateFlow<AppState>(AppState.None)
    val appState: StateFlow<AppState> = _appState

    fun reg(user: User) {
        println("RegViewModel регистрация: $user")

        viewModelScope.launch {
            _appState.emit(AppState.Loading)

            val result = registerUseCase(user)
            println("RegViewModel result: $result")
            if (user.login != null && user.password != null) {
                _appState.emit(
                    AppState.Success(
                        Triple(
                            result,
                            user.login,
                            user.password
                        )
                    )
                )
            } else {
                println("логин или пароль пустой. сюда ветка не должна заходить")
                _appState.emit(AppState.Error(error = Throwable("error login or pass are null")))
            }
        }
    }

    class Factory(
        private val registerUseCase: RegisterUseCase
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(RegViewModel::class.java)) {
                return RegViewModel(
                    registerUseCase = registerUseCase,
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}