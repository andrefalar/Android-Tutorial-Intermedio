package com.andrefalar.horoscapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrefalar.horoscapp.domain.usecase.GetPredictionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HoroscopeDetailViewModel @Inject constructor(private val getPredictionUseCase: GetPredictionUseCase) :
    ViewModel() {

    // Se declara una variable mutable `_state` que almacena el estado actual del detalle del horóscopo
    private var _state = MutableStateFlow<HoroscopeDetailState>(HoroscopeDetailState.Loading)

    // Se declara una variable `state` que expone un flujo de estado inmutable a otras clases
    val state: StateFlow<HoroscopeDetailState> = _state


    fun getHoroscope(sign: String) {
        // Ejecuta una corutina en el ViewModel
        viewModelScope.launch {
            // cambia el estado del Detail a Loading
            _state.value = HoroscopeDetailState.Loading
            // Selecciona el hilo que se emplea para  operaciones de entrada/salida (I/O) y lo asigna a result
            val result = withContext(Dispatchers.IO) {
                // Utiliza el metodo invoke del UseCase
                getPredictionUseCase(sign)
            }

            if (result!=null){
                _state.value = HoroscopeDetailState.Success(result.horoscope, result.sign)
            } else {
                _state.value = HoroscopeDetailState.Error("Ha ocurrido un error, intentelo mas tarde.")
            }
        }
    }

}