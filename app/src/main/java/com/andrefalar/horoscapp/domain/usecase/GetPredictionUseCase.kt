package com.andrefalar.horoscapp.domain.usecase

import com.andrefalar.horoscapp.domain.Repository
import javax.inject.Inject

class GetPredictionUseCase @Inject constructor(private val repository: Repository) {

    /*
    La palabra clave "operator" permite sobreescribir algunas funciones de invocacion,
    mientras que el operador "fun invoke" me permite llamar el metodo sin necesidad de asignarle
    un nombre, es suficiente con poner el nombre de la funcion y usar "()".
     */

    // obtiene la prediccion del signo
    suspend operator fun invoke(sign: String) = repository.getPrediction(sign)

}