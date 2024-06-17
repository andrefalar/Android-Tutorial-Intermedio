package com.andrefalar.horoscapp.domain

import com.andrefalar.horoscapp.domain.model.PredictionModel

// Es la comunicacion entre la capa de data y la capa de dominio
interface Repository {
    suspend fun getPrediction(sign:String): PredictionModel?
}