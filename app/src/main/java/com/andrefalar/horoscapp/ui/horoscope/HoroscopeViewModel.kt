package com.andrefalar.horoscapp.ui.horoscope

import androidx.lifecycle.ViewModel
import com.andrefalar.horoscapp.data.providers.HoroscopeProvider
import com.andrefalar.horoscapp.domain.model.HoroscopeInfo
import com.andrefalar.horoscapp.domain.model.HoroscopeInfo.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

// indicador para inyeccion de dependecias en ViewModels
@HiltViewModel
class HoroscopeViewModel @Inject constructor(private val horoscopeProvider: HoroscopeProvider):ViewModel(){

    /*
    Creamos un stateflow para una comunicacion constante entre el fragment y el viewmodel, ademas
    se pone en practica el encapsulamiento, esta práctica de encapsular un MutableStateFlow y
    exponer un StateFlow inmutable es una excelente manera de mantener la integridad del estado
    interno de una clase mientras se permite que otros componentes reaccionen a los cambios de
    manera segura y controlada.
     */
    private var _horoscope = MutableStateFlow<List<HoroscopeInfo>>(emptyList())
    val horoscope: StateFlow<List<HoroscopeInfo>> = _horoscope

    // Este metodo se llama justo cuando se crea el ViewModel
    init {
        //
        _horoscope.value = horoscopeProvider.getHoroscopes()
    }
}