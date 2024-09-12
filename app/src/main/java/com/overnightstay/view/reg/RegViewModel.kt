package com.overnightstay.view.reg

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.overnightstay.domain.usecases.RegisterUseCase

class RegViewModel(private val registerUseCase : RegisterUseCase) : ViewModel() {

    class Factory(
        private val registerUseCase : RegisterUseCase
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