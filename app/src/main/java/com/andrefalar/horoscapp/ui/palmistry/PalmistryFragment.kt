package com.andrefalar.horoscapp.ui.palmistry

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import com.andrefalar.horoscapp.databinding.FragmentPalmistryBinding
import dagger.hilt.android.AndroidEntryPoint

// indicador para inyeccion de dependecias para componentes
@AndroidEntryPoint
class PalmistryFragment : Fragment() {

    companion object{
        private const val CAMERA_PERMISSION = Manifest.permission.CAMERA
    }

    private var _binding: FragmentPalmistryBinding? = null
    private val binding get() = _binding!!

    // Lanza la solicitud de permiso a la camara
    private val requestPermissionsLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGrandted ->
        if (isGrandted) {
            startCamera()
        } else {
            Toast.makeText(
                requireContext(),
                "Please, accept the permissions if you want to use this feature.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Procede de acuerdo a los permisos de camara
        if (checkCameraPermission()) {
            // Los permisos estan aceptados
            startCamera()
        } else {
            // Si no esta aceptado inicia el launcher para solicitar el permiso
            requestPermissionsLauncher.launch(CAMERA_PERMISSION)
        }

    }

    private fun startCamera() {
        // Obtener una instancia de ProcessCameraProvider, que es responsable de enlazar la cámara con el ciclo de vida
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        // Agregar un listener que se ejecutará una vez que la instancia de cameraProviderFuture esté disponible
        cameraProviderFuture.addListener({
            // Obtener la instancia de ProcessCameraProvider
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            // Configurar la vista previa de la cámara
            val preview = Preview.Builder()
                .build()
                .also {
                    // Establecer el SurfaceProvider para la vista previa, que es el componente que mostrará lo que la cámara está capturando
                    it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
                }
            // Seleccionar la cámara trasera como la cámara a usar
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                // Desenlazar cualquier uso previo de la cámara para evitar conflictos
                cameraProvider.unbindAll()
                // Enlazar la vista previa de la cámara al ciclo de vida de esta actividad/fragmento, usando la cámara seleccionada
                cameraProvider.bindToLifecycle(this, cameraSelector, preview)
            } catch (e: Exception) {
                // Manejar cualquier excepción que ocurra durante el proceso de enlace de la cámara
                Log.e("Andres", "Algo en la cámara falló: ${e.message}")
            }
        }, ContextCompat.getMainExecutor(requireContext()))
        // ContextCompat.getMainExecutor se usa para ejecutar el listener en el hilo principal
    }


    // Se encarga de chequear el permiso de acceso a la camara
    fun checkCameraPermission(): Boolean {
        return PermissionChecker.checkSelfPermission(
            requireContext(),
            CAMERA_PERMISSION
        ) == PermissionChecker.PERMISSION_GRANTED
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPalmistryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}