package com.andrefalar.horoscapp.ui.horoscope.adapter

import android.view.View
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.RecyclerView
import com.andrefalar.horoscapp.databinding.ItemHoroscopeBinding
import com.andrefalar.horoscapp.domain.model.HoroscopeInfo

// Pinta las vistas
class HoroscopeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    // Bindeamos la vista que recibamos por parametro
    private val binding = ItemHoroscopeBinding.bind(view)

    // renderiza la vista
    fun render(horoscopeInfo: HoroscopeInfo, onItemSelected: (HoroscopeInfo) -> Unit) {
        binding.ivHoroscope.setImageResource(horoscopeInfo.img)
        binding.tvHoroscope.setText(horoscopeInfo.name)

        // listener al ser selecionado
        binding.parent.setOnClickListener {
            starRotationAnimation(
                binding.ivHoroscope,
                newLambda = { onItemSelected(horoscopeInfo) })
        }
    }

    // inicia una animacion sencilla
    fun starRotationAnimation(view: View, newLambda: () -> Unit) {
        // Aplica la animacion con sus propiedades correspondientes
        view.animate().apply {
            // Se configura en milisegundos
            duration = 500
            // Flujo de tiempo de la animacion
            interpolator = LinearInterpolator()
            rotationBy(360f)
            // ejeucuta la funcion al finalizar la accion
            withEndAction(newLambda)
            start()
        }
    }
}