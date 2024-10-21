package com.overnightstay.view.house_of_distribution.minigame

sealed class GameMemoDistrState {
    companion object {
        //-1 чтобы словить начало игры и вывести сообщение
        var count: Int = -1
    }

    data object MemoStart : GameMemoDistrState()
    data object MemoFinish : GameMemoDistrState()
    data object MemoReverseCard : GameMemoDistrState()

    data class MemoOpenedCard<out T>(val data: T)  : GameMemoDistrState()
    data class MemoCheckingCards<out T>(val data: T) : GameMemoDistrState()
}
