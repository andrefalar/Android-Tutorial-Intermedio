package com.andrefalar.horoscapp.ui.horoscope

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andrefalar.horoscapp.databinding.FragmentHoroscopeBinding


class HoroscopeFragment : Fragment() {

    // Creamos el binding del fragment (_binding indica que debe ser privada)
    private var _binding: FragmentHoroscopeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // configuramos el inflate
        _binding = FragmentHoroscopeBinding.inflate(layoutInflater, container, false)
        // se retorna
        return binding.root
    }


}