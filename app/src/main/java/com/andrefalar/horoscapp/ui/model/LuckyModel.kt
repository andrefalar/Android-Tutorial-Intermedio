package com.andrefalar.horoscapp.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/*
Este modelo de datos esta en UI porque debe ser una referencia a recursos de android
obligatoriamente.
 */

data class LuckyModel (
    @DrawableRes val image:Int,
    @StringRes val text:Int
)
