package com.overnightstay.view.night_bus.finishminigame

sealed class FihishMiniGameState {
    data class Stage1<out T>(val data: T) : FihishMiniGameState()
    data class Stage2<out T>(val data: T) : FihishMiniGameState()
    data class Stage3<out T>(val data: T) : FihishMiniGameState()
    data class Stage4<out T>(val data: T) : FihishMiniGameState()
    data class StageFinish<out T>(val data: T) : FihishMiniGameState()
    data class Error(val error: Throwable) : FihishMiniGameState()
    data object Loading : FihishMiniGameState()
}
