package com.overnightstay.view.reg

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.overnightstay.domain.models.User
import com.overnightstay.domain.usecases.RegisterUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class RegViewModel(private val registerUseCase: RegisterUseCase) : ViewModel() {
    private var _isEntry = MutableSharedFlow<Triple<Boolean, String, String>>()
    val isEntry: SharedFlow<Triple<Boolean, String, String>>
        get() = _isEntry.asSharedFlow()

    fun reg(user: User) {
        println("MVVM регистрация: $user")

        viewModelScope.launch {
            val result = registerUseCase(user)
            println("MVVM result: $result")
            if (user.login != null && user.password != null) _isEntry.emit(
                Triple(
                    result,
                    user.login,
                    user.password
                )
            )
            else println("логин или пароль пустой. сюда ветка не должна заходить")
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