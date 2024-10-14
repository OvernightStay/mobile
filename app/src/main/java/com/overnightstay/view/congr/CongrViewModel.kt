package com.overnightstay.view.congr

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.overnightstay.domain.AppState
import com.overnightstay.domain.models.User
import com.overnightstay.domain.usecases.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CongrViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {
    private val _appState = MutableStateFlow<AppState>(AppState.None)
    val appState: StateFlow<AppState> = _appState

    fun login(user: User) {
        viewModelScope.launch {
            _appState.emit(AppState.Loading)

            val result = loginUseCase(user)
            println("CongrViewModel result: $result")
            _appState.emit(AppState.Success(result.first))
        }
    }

    class Factory(
        private val loginUseCase: LoginUseCase,
        ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CongrViewModel::class.java)) {
                return CongrViewModel(
                    loginUseCase = loginUseCase,
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}