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

    private val _isGameOver = MutableStateFlow(false)
    val isGameOver: StateFlow<Boolean> = _isGameOver

    fun timerFinish() {
        viewModelScope.launch {
            _countNegativ.value++
            if (_countNegativ.value + _countPositiv.value < 10) _countNegativ.emit(_countNegativ.value)
            else gameOver()
        }
    }

    fun addPositiv() {
        viewModelScope.launch {
            _countPositiv.value++
            if (_countNegativ.value + _countPositiv.value < 10) _countPositiv.emit(_countPositiv.value)
            else gameOver()
        }
    }

    fun gameOver() {
        viewModelScope.launch {
            _isGameOver.emit(true)
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