package com.overnightstay.view.book.contents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.overnightstay.domain.AppState
import com.overnightstay.domain.usecases.GetPlayerFromPrefUseCase
import com.overnightstay.domain.usecases.UpdatePlayerOnApiUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ContentsOfBookViewModel(

    ) : ViewModel() {
    private val _appState = MutableStateFlow<AppState>(AppState.None)
    val appState: StateFlow<AppState> = _appState




    class Factory(
        ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ContentsOfBookViewModel::class.java)) {
                return ContentsOfBookViewModel(
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}