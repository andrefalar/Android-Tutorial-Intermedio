package com.andrefalar.horoscapp.ui.horoscope

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.andrefalar.horoscapp.databinding.FragmentHoroscopeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

// indicador para inyeccion de dependecias para componentes
@AndroidEntryPoint
class HoroscopeFragment : Fragment() {

    // Conecta el Fragment con el ViewModel correspondiente
    private val horoscopeViewModel by viewModels<HoroscopeViewModel>()

    // Creamos el binding del fragment (_binding indica que debe ser privada)
    private var _binding: FragmentHoroscopeBinding? = null
    private val binding get() = _binding!!

    // Se llama cuando se crea la vista
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // configuramos el inflate
        _binding = FragmentHoroscopeBinding.inflate(layoutInflater, container, false)
        // se retorna
        return binding.root
    }

    // se llama cuando la vista fue creada
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    private fun initUi() {
        initUIState()
    }

    private fun initUIState() {
        // Creamos esta corutina en especifico ya que se engancha al ciclo de vida del fragmento
        lifecycleScope.launch {
            // Cuando empiece el ciclo de vida...
            repeatOnLifecycle(Lifecycle.State.STARTED){
                // Suscribete al StateFlow del ViewModel
                horoscopeViewModel.horoscope.collect{
                    Log.i("Aris",it.toString())
                }
            }
        }
    }
}