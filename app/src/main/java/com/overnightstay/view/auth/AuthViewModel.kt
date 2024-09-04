package com.overnightstay.view.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.overnightstay.domain.usecases.LoginUseCase

class AuthViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

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