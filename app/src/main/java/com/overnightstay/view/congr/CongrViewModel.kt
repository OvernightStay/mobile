package com.overnightstay.view.congr

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.overnightstay.domain.models.User
import com.overnightstay.domain.usecases.LoginUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch


class CongrViewModel(private val loginUseCase: LoginUseCase) : ViewModel() {
    private var _isEntry = MutableSharedFlow<Boolean>()
    val isEntry: SharedFlow<Boolean>
        get() = _isEntry.asSharedFlow()

    fun login(user: User) {
        viewModelScope.launch {
            val result = loginUseCase(user)
            println("MVVM result: $result")
            _isEntry.emit(result)
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