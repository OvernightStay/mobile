package com.overnightstay.view.house_of_distribution.finishminigame

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FinishGameMemoDistrViewModel : ViewModel() {
    private val _gameStageFlow = MutableStateFlow(FinishGameMemoDistrEnum.DISTRIBUTION_FINISH_GAME_1)
    val gameStageFlow: StateFlow<FinishGameMemoDistrEnum> = _gameStageFlow

    fun nextStage() {
        viewModelScope.launch {
            _gameStageFlow.value.next()?.let { _gameStageFlow.emit(it) }
        }
    }

    class Factory(
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FinishGameMemoDistrViewModel::class.java)) {
                return FinishGameMemoDistrViewModel(
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}