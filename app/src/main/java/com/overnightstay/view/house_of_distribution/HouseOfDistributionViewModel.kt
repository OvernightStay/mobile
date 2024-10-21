package com.overnightstay.view.house_of_distribution

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HouseOfDistributionViewModel : ViewModel() {
    private val _gameStageFlow = MutableStateFlow(HouseOfDistributionEnum.DISTRIBUTION_STAGE1_1)
    val gameStageFlow: StateFlow<HouseOfDistributionEnum> = _gameStageFlow

    fun nextStage() {
        viewModelScope.launch {
            _gameStageFlow.value.next()?.let { _gameStageFlow.emit(it) }
        }
    }

    class Factory(
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HouseOfDistributionViewModel::class.java)) {
                return HouseOfDistributionViewModel(
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}