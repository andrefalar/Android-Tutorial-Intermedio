package com.andrefalar.horoscapp.domain.model

/*
Esto se hace para que este modeeo no contenga ninguna libreria de Android y asi los datos puedan
ser consumidos por otras tecnologias.
 */
data class PredictionModel (
    val horoscope:String,
    val sign:String
)