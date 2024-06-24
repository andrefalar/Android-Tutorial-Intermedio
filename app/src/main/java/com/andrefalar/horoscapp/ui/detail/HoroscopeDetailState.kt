package com.andrefalar.horoscapp.ui.detail

import com.andrefalar.horoscapp.domain.model.HoroscopeModel

// Define los posibles estados de la activity Detail
sealed class HoroscopeDetailState {
    data object Loading : HoroscopeDetailState()
    data class Error(val error: String) : HoroscopeDetailState()
    data class Success(
        val prediction: String,
        val sign: String,
        val horoscopeModel: HoroscopeModel
    ) : HoroscopeDetailState()
}