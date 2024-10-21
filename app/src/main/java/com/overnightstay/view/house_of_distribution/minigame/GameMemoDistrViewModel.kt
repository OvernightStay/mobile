package com.overnightstay.view.house_of_distribution.minigame

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.overnightstay.domain.models.IndexMemoGame
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GameMemoDistrViewModel : ViewModel() {
    private val _gameMemoStateFlow =
        MutableStateFlow<GameMemoDistrState>(GameMemoDistrState.MemoStart)
    val gameMemoStateFlow: StateFlow<GameMemoDistrState> = _gameMemoStateFlow

    fun openCard(i: Int, j: Int) {
        when(_gameMemoStateFlow.value){
            is GameMemoDistrState.MemoCheckingCards<*> -> {}
            is GameMemoDistrState.MemoFinish<*> -> {}
            is GameMemoDistrState.MemoFirstMatch<*> -> {}
            is GameMemoDistrState.MemoLastMatch<*> -> {}
            is GameMemoDistrState.MemoMatchAfterNot<*> -> {}
            is GameMemoDistrState.MemoNotMatch<*> -> {}

            is GameMemoDistrState.MemoOpenedCard<*> -> {
                if ((_gameMemoStateFlow.value as GameMemoDistrState.MemoOpenedCard<*>).data is IndexMemoGame) {

                    viewModelScope.launch {
                        _gameMemoStateFlow.emit(
                            GameMemoDistrState.MemoCheckingCards(
                                Pair(
                                    (_gameMemoStateFlow.value as GameMemoDistrState.MemoOpenedCard<*>).data, IndexMemoGame(i, j)
                                )
                            )
                        )
                    }

                } else {
                    throw IllegalArgumentException("в этом состоянии должно быть данные индекса открытой карточки")
                }
            }
            is GameMemoDistrState.MemoSecondMatch<*> -> {}
            GameMemoDistrState.MemoStart -> {}
            is GameMemoDistrState.MemoSuccess<*> -> {}
            is GameMemoDistrState.MemoReverseCard<*> -> {
                viewModelScope.launch {
                    _gameMemoStateFlow.emit(GameMemoDistrState.MemoOpenedCard(IndexMemoGame(i, j)))
                }
            }
        }
    }

    fun allReverseCards() {
        viewModelScope.launch {
            _gameMemoStateFlow.emit(GameMemoDistrState.MemoReverseCard(null))
        }
    }

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