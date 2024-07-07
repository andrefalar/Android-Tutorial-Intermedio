package com.andrefalar.horoscapp.ui.palmistry

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.PermissionChecker
import com.andrefalar.horoscapp.Manifest
import com.andrefalar.horoscapp.R
import com.andrefalar.horoscapp.databinding.FragmentPalmistryBinding
import dagger.hilt.android.AndroidEntryPoint

// indicador para inyeccion de dependecias para componentes
@AndroidEntryPoint
class PalmistryFragment : Fragment() {

    companion object{
        private const val CAMERA_PERMISSION = android.Manifest.permission.CAMERA
    }

    private var _pinding: FragmentPalmistryBinding? = null
    private val pinding get() = _pinding!!

    // Lanza la solicitud de permiso a la camara
    private val requestPermissionsLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGrandted ->
        if (isGrandted) {

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
        } else {
            // No estan aceptados
            requestPermissionsLauncher.launch(CAMERA_PERMISSION)
        }

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
        _pinding = FragmentPalmistryBinding.inflate(layoutInflater, container, false)
        return pinding.root
    }

}