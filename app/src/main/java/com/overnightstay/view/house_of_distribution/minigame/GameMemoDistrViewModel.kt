package com.overnightstay.view.house_of_distribution.minigame

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GameMemoDistrViewModel : ViewModel() {

    class Factory(
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(GameMemoDistrViewModel::class.java)) {
                return GameMemoDistrViewModel(
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}