package com.andrefalar.horoscapp.ui.horoscope.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.andrefalar.horoscapp.databinding.ItemHoroscopeBinding
import com.andrefalar.horoscapp.domain.model.HoroscopeInfo

// Pinta las vistas
class HoroscopeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    // Bindeamos la vista que recibamos por parametro
    private val binding = ItemHoroscopeBinding.bind(view)
    fun render(horoscopeInfo: HoroscopeInfo){
        binding.ivHoroscope.setImageResource(horoscopeInfo.img)
        binding.tvHoroscope.setText(horoscopeInfo.name)

    }
}