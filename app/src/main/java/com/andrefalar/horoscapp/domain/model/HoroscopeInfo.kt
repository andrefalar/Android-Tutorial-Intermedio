package com.andrefalar.horoscapp.domain.model

import com.andrefalar.horoscapp.R

/*
Creamos una sealed class para el modelo de datos ya que se trata de informacion
constante y poco compleja , lo que nos facilitara su manipulacion durante el desarrollo
 */

/*
Al estar en el domain del proyecto debemos utilizar parametros de tipo int al crear la clase,
ya que debe ser versatil entre distintas tecnologias, pues se trata de la logica del negocio.
*/

sealed class HoroscopeInfo(val img: Int, val name: Int) {
    object Aries : HoroscopeInfo(R.drawable.aries, R.string.aries)
    object Taurus : HoroscopeInfo(R.drawable.tauro, R.string.taurus)
    object Gemini : HoroscopeInfo(R.drawable.geminis, R.string.gemini)
    object Cancer : HoroscopeInfo(R.drawable.cancer, R.string.cancer)
    object Leo : HoroscopeInfo(R.drawable.leo, R.string.leo)
    object Virgo : HoroscopeInfo(R.drawable.virgo, R.string.virgo)
    object Libra : HoroscopeInfo(R.drawable.libra, R.string.libra)
    object Scorpio : HoroscopeInfo(R.drawable.escorpio, R.string.scorpio)
    object Sagittarius : HoroscopeInfo(R.drawable.sagitario, R.string.sagittarius)
    object Capricorn : HoroscopeInfo(R.drawable.capricornio, R.string.capricorn)
    object Aquarius : HoroscopeInfo(R.drawable.aquario, R.string.aquarius)
    object Pisces : HoroscopeInfo(R.drawable.piscis, R.string.pisces)
}

