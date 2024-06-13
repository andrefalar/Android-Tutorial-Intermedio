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
        // cargamos datos traido de otra pantalla
        args.type
        initUI()
    }

    private fun initUI() {
        initUIState()
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
                        is HoroscopeDetailState.Success -> successState()
                    }
                }
            }
        }
    }

    private fun loadingState(){
        binding.pbDetail.isVisible = true
    }

    private fun errorState(){

    }
    private fun successState(){

    }
}