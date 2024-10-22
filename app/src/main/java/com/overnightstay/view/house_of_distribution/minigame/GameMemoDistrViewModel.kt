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

    private val _count = MutableStateFlow(GameMemoDistrState.count)
    val count: StateFlow<Int> = _count

    fun openCard(i: Int, j: Int) {
        when(_gameMemoStateFlow.value){
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
            is GameMemoDistrState.MemoReverseCard -> {
                viewModelScope.launch {
                    _gameMemoStateFlow.emit(GameMemoDistrState.MemoOpenedCard(IndexMemoGame(i, j)))
                }
            }

            else ->{}
//            GameMemoDistrState.MemoStart -> {}
//            is GameMemoDistrState.MemoFinish<*> -> {}
//            is GameMemoDistrState.MemoCheckingCards<*> -> {}
        }
    }

    fun allReverseCards() {
        viewModelScope.launch {
            _gameMemoStateFlow.emit(GameMemoDistrState.MemoReverseCard)
        }
    }

    fun addCount() {
        println("счетчик пар мемо = ${GameMemoDistrState.count}")
        viewModelScope.launch {
            _count.emit(++GameMemoDistrState.count)
        }
    }

    fun finish() {
        viewModelScope.launch {
            _gameMemoStateFlow.emit(GameMemoDistrState.MemoFinish)
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