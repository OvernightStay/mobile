package com.overnightstay.view.choose_pers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.overnightstay.domain.usecases.GetPlayerFromPrefUseCase
import com.overnightstay.domain.usecases.UpdatePlayerOnApiUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class ChoosePersViewModel(
    private val getPlayerFromPrefUseCase: GetPlayerFromPrefUseCase,
    private val updatePlayerOnApiUseCase: UpdatePlayerOnApiUseCase,
    ) : ViewModel() {
    private var _userName = MutableSharedFlow<String>()
    val userName: SharedFlow<String>
        get() = _userName.asSharedFlow()

    private var _isEntry = MutableSharedFlow<Boolean>()
    val isEntry: SharedFlow<Boolean>
        get() = _isEntry.asSharedFlow()

    fun getPlayerFromPref() {
        viewModelScope.launch {
            val user = getPlayerFromPrefUseCase()
            println("ChoosePersViewModel user: $user")
            _userName.emit("${user.first_name} ${user.last_name}")
        }
    }

    fun updatePlayer(userName: String, gender: String) {
        viewModelScope.launch {
            val result = updatePlayerOnApiUseCase(userName, gender)
            _isEntry.emit(result)
        }
    }


    class Factory(
        private val getPlayerFromPrefUseCase: GetPlayerFromPrefUseCase,
        private val updatePlayerOnApiUseCase: UpdatePlayerOnApiUseCase,
        ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ChoosePersViewModel::class.java)) {
                return ChoosePersViewModel(
                    getPlayerFromPrefUseCase = getPlayerFromPrefUseCase,
                    updatePlayerOnApiUseCase = updatePlayerOnApiUseCase,
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}