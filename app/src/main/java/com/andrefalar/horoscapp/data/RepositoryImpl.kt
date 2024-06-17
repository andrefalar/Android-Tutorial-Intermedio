package com.andrefalar.horoscapp.data

import android.util.Log
import com.andrefalar.horoscapp.data.network.HoroscopeApiService
import com.andrefalar.horoscapp.domain.Repository
import com.andrefalar.horoscapp.domain.model.PredictionModel
import javax.inject.Inject

// con este repositoro ya se pueden realizar peticiones a internet
class RepositoryImpl @Inject constructor(private val apiService: HoroscopeApiService): Repository {
    override suspend fun getPrediction(sign: String):PredictionModel? {
        // Peticion Retrofit
        runCatching { apiService.getHoroscope(sign) }
            .onSuccess { return it.toDomain() }
            .onFailure { Log.i("Andres", "Ha ocurrido un error ${it.message}") }
        return null
    }
}