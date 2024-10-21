package com.overnightstay.view.house_of_distribution.minigame

sealed class GameMemoDistrState {
    companion object {
        var count: Int = 0
    }

    data object MemoStart : GameMemoDistrState()
    data class MemoFirstMatch<out T>(val data: T) : GameMemoDistrState()
    data class MemoSecondMatch<out T>(val data: T) : GameMemoDistrState()
    data class MemoNotMatch<out T>(val data: T) : GameMemoDistrState()
    data class MemoMatchAfterNot<out T>(val data: T) : GameMemoDistrState()
    data class MemoLastMatch<out T>(val data: T) : GameMemoDistrState()

    data class MemoOpenedCard<out T>(val data: T)  : GameMemoDistrState()
    data class MemoCheckingCards<out T>(val data: T) : GameMemoDistrState()
    data class MemoSuccess<out T>(val data: T) : GameMemoDistrState()
    data class MemoFinish<out T>(val data: T) : GameMemoDistrState()
    data class MemoReverseCard<out T>(val data: T) : GameMemoDistrState()
}
