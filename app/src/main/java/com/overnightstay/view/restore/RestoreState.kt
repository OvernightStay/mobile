package com.overnightstay.view.restore

sealed class RestoreState {
    data class GetCode<out T>(val data: T) : RestoreState()
    data class Confirm<out T>(val data: T) : RestoreState()
    data class RePass<out T>(val data: T) : RestoreState()
    data class Done<out T>(val data: T) : RestoreState()
    data class Error(val error: Throwable) : RestoreState()
    data object Loading : RestoreState()
}
