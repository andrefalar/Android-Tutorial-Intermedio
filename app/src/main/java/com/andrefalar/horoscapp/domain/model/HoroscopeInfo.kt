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

/*
Que los objetos de la sealed class puedan ser de tipo data object es una nueva funcionalidad de
kotlin, si lo imprimimos a traves de un log se vera de manera mas legible
*/

sealed class HoroscopeInfo(val img: Int, val name: Int) {
    data object Aries : HoroscopeInfo(R.drawable.aries, R.string.aries)
    data object Taurus : HoroscopeInfo(R.drawable.tauro, R.string.taurus)
    data object Gemini : HoroscopeInfo(R.drawable.geminis, R.string.gemini)
    data object Cancer : HoroscopeInfo(R.drawable.cancer, R.string.cancer)
    data object Leo : HoroscopeInfo(R.drawable.leo, R.string.leo)
    data object Virgo : HoroscopeInfo(R.drawable.virgo, R.string.virgo)
    data object Libra : HoroscopeInfo(R.drawable.libra, R.string.libra)
    data object Scorpio : HoroscopeInfo(R.drawable.escorpio, R.string.scorpio)
    data object Sagittarius : HoroscopeInfo(R.drawable.sagitario, R.string.sagittarius)
    data object Capricorn : HoroscopeInfo(R.drawable.capricornio, R.string.capricorn)
    data object Aquarius : HoroscopeInfo(R.drawable.aquario, R.string.aquarius)
    data object Pisces : HoroscopeInfo(R.drawable.piscis, R.string.pisces)
}

