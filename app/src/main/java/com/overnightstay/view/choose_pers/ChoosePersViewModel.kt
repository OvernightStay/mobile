package com.overnightstay.view.choose_pers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.overnightstay.domain.AppState
import com.overnightstay.domain.usecases.GetPlayerFromPrefUseCase
import com.overnightstay.domain.usecases.UpdatePlayerOnApiUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChoosePersViewModel(
    private val getPlayerFromPrefUseCase: GetPlayerFromPrefUseCase,
    private val updatePlayerOnApiUseCase: UpdatePlayerOnApiUseCase,
    ) : ViewModel() {
    private val _appState = MutableStateFlow<AppState>(AppState.None)
    val appState: StateFlow<AppState> = _appState

    fun getPlayerFromPref() {
        viewModelScope.launch {
            _appState.emit(AppState.Loading)

            val user = getPlayerFromPrefUseCase()
            println("ChoosePersViewModel user: $user")
            _appState.emit(AppState.Success(user))
        }
    }

    fun updatePlayer(userName: String, gender: String) {
        viewModelScope.launch {
            _appState.emit(AppState.Loading)
            val result = updatePlayerOnApiUseCase(userName, gender)
            _appState.emit(AppState.Success(result))
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