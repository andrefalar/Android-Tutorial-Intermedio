package com.andrefalar.horoscapp.ui.horoscope

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.andrefalar.horoscapp.databinding.FragmentHoroscopeBinding
import com.andrefalar.horoscapp.ui.horoscope.adapter.HoroscopeAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

// indicador para inyeccion de dependecias para componentes
@AndroidEntryPoint
class HoroscopeFragment : Fragment() {

    // Conecta el Fragment con el ViewModel correspondiente
    private val horoscopeViewModel by viewModels<HoroscopeViewModel>()

    // Declaramos e importamos adapter
    private lateinit var horoscopeAdapter: HoroscopeAdapter

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
        initRecyclerView()
        initUIState()
    }

    // Inicializa el adapter del RecyclerView
    private fun initRecyclerView() {
        // Al incializar el adapter pasamos una lambda que nos indique que elemento fue selecionado
        horoscopeAdapter = HoroscopeAdapter(onItemSelected = {
            // Devuelven el item que fue seleccionado (it hace referencia al item en el ViewHolder)
            Toast.makeText(context, getString(it.name),Toast.LENGTH_LONG).show()
        })

        // configura el RecyclerView del layout
        binding.rvHoroscope.apply {
            // Para que se muestre en cuadricula
            layoutManager = GridLayoutManager(context, 2)
            adapter = horoscopeAdapter
        }
    }

    // inicializa y maneja el estado de la interfaz de usuario (UI) en el fragmento
    private fun initUIState() {
        // Creamos esta corutina en especifico ya que se engancha al ciclo de vida del fragmento
        lifecycleScope.launch {
            // Cuando empiece el ciclo de vida...
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Suscribete al StateFlow del ViewModel
                horoscopeViewModel.horoscope.collect {
                    // Actualiza el adapter deacuerdo al modelo de datos del ViewModel
                    horoscopeAdapter.updateList(it)
                }
            }
        }
    }
}