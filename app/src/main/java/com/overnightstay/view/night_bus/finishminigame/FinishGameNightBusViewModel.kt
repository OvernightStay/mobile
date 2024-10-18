package com.overnightstay.view.night_bus.finishminigame

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FinishGameNightBusViewModel : ViewModel() {
    private val _gameStateFlow = MutableStateFlow<FihishMiniGameState>(FihishMiniGameState.Stage1(null))
    val gameStateFlow: StateFlow<FihishMiniGameState> = _gameStateFlow

    fun nextStage() {
        viewModelScope.launch {
            when (_gameStateFlow.value) {
                is FihishMiniGameState.Stage1<*> -> {
                    _gameStateFlow.emit(FihishMiniGameState.Stage2(null))
                }

                is FihishMiniGameState.Stage2<*> -> {
                    _gameStateFlow.emit(FihishMiniGameState.Stage3(null))
                }

                is FihishMiniGameState.Stage3<*> -> {
                    _gameStateFlow.emit(FihishMiniGameState.Stage4(null))
                }

                is FihishMiniGameState.Stage4<*> -> {
                    _gameStateFlow.emit(FihishMiniGameState.StageFinish(null))
                }

                is FihishMiniGameState.StageFinish<*> -> {

                }

                is FihishMiniGameState.Error -> {

                }

                FihishMiniGameState.Loading -> {

                }
            }
        }
    }

    fun takeAchieve() {
        nextStage()
    }


    class Factory(
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FinishGameNightBusViewModel::class.java)) {
                return FinishGameNightBusViewModel(
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}