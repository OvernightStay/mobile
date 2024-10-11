package com.overnightstay.domain

sealed class AppState {
    data class Success<out T>(val data: T) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
    object None : AppState()
}
