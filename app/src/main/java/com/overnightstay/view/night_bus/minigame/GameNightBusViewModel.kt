package com.overnightstay.view.night_bus.minigame

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GameNightBusViewModel : ViewModel() {
    private val _countNegativ = MutableStateFlow(0)
    val countNegativ: StateFlow<Int> = _countNegativ

    private val _countPositiv = MutableStateFlow(0)
    val countPositiv: StateFlow<Int> = _countPositiv

    fun timerFinish() {
        viewModelScope.launch {
            _countNegativ.emit(++_countNegativ.value)
        }
    }

    fun addPositiv() {
        viewModelScope.launch {
            _countPositiv.emit(++_countPositiv.value)
        }
    }

    class Factory(
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(GameNightBusViewModel::class.java)) {
                return GameNightBusViewModel(
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}