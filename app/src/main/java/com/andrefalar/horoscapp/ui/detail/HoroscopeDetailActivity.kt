package com.andrefalar.horoscapp.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navArgs
import com.andrefalar.horoscapp.R
import com.andrefalar.horoscapp.databinding.ActivityHoroscopeDetailBinding
import com.andrefalar.horoscapp.domain.model.HoroscopeModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HoroscopeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHoroscopeDetailBinding

    // Otra fomar de declarar el viewModel
    private val horoscopeDetailViewModel: HoroscopeDetailViewModel by viewModels()

    // Declaramos los argumentos para traer datos de otra activity o fragment
    private val args: HoroscopeDetailActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHoroscopeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Obtener horoscopo del signo seleccionado
        horoscopeDetailViewModel.getHoroscope(args.type)
        initUI()
    }

    private fun initUI() {
        initListener()
        initUIState()
    }

    // inicia listeners de la pantalla
    private fun initListener() {
        // vuelve a la pantalla anterior al ser pulsado
        binding.ivBack.setOnClickListener { onBackPressed() }
    }

    // Inicializa el estado de la interfaz de usuario
    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                horoscopeDetailViewModel.state.collect {
                    // Al tratarse de varios estados esto se ejecutara segun su valor
                    when (it) {
                        HoroscopeDetailState.Loading -> loadingState()
                        is HoroscopeDetailState.Error -> errorState()
                        is HoroscopeDetailState.Success -> successState(it)
                    }
                }
            }
        }
    }

    // Cada funcion configura la pantalla de detail deacuerdo al estado de la request

    private fun loadingState() {
        binding.pbDetail.isVisible = true
    }

    private fun errorState() {
        binding.pbDetail.isVisible = false
    }

    private fun successState(state: HoroscopeDetailState.Success) {
        binding.pbDetail.isVisible = false
        binding.tvDetailTitle.text = state.sign
        binding.tvDetailBody.text = state.prediction

        val image = when (state.horoscopeModel) {
            HoroscopeModel.Aries -> R.drawable.detail_aries
            HoroscopeModel.Taurus -> R.drawable.detail_taurus
            HoroscopeModel.Gemini -> R.drawable.detail_gemini
            HoroscopeModel.Cancer -> R.drawable.detail_cancer
            HoroscopeModel.Leo -> R.drawable.detail_leo
            HoroscopeModel.Virgo -> R.drawable.detail_virgo
            HoroscopeModel.Libra -> R.drawable.detail_libra
            HoroscopeModel.Scorpio -> R.drawable.detail_scorpio
            HoroscopeModel.Sagittarius -> R.drawable.detail_sagittarius
            HoroscopeModel.Capricorn -> R.drawable.detail_capricorn
            HoroscopeModel.Aquarius -> R.drawable.detail_aquarius
            HoroscopeModel.Pisces -> R.drawable.detail_pisces
        }

        binding.ivDetail.setImageResource(image)
    }
}