package com.andrefalar.horoscapp.ui.detail

// Define los posibles estados de la activity Detail
sealed class HoroscopeDetailState {
    data object Loading:HoroscopeDetailState()
    data class Error(val error:String):HoroscopeDetailState()
    data class Success(val prediction:String, val sign:String):HoroscopeDetailState()
}