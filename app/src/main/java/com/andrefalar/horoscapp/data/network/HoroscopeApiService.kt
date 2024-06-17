package com.andrefalar.horoscapp.data.network

import com.andrefalar.horoscapp.data.network.response.PredictionResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface HoroscopeApiService {

    // Realiza la solicitud de acuerdo a al signo
    @GET("/{sign}")

    // Es de tipo suspend ya que se ejecutara en una corutina
    suspend fun getHoroscope(@Path("sign") sign:String):PredictionResponse
}