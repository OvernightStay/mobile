package com.overnightstay.view.choose_pers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.overnightstay.domain.models.User
import com.overnightstay.domain.usecases.GetPlayerFromApiUseCase
import com.overnightstay.view.auth.AuthViewModel
import kotlinx.coroutines.launch

class ChoosePersViewModel(
    private val getPlayerFromApiUseCase: GetPlayerFromApiUseCase
) : ViewModel() {

    fun getPlayer(user: User) {
        println("MVVM login: ${user.login}")

        viewModelScope.launch {

        }
    }


    class Factory(
        private val getPlayerFromApiUseCase: GetPlayerFromApiUseCase,

        ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ChoosePersViewModel::class.java)) {
                return ChoosePersViewModel(
                    getPlayerFromApiUseCase = getPlayerFromApiUseCase,
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}