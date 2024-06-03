package com.andrefalar.horoscapp.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.andrefalar.horoscapp.R
import com.andrefalar.horoscapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Clase padre que gestiona la navegacion
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    private fun initUI() {
        initNavigation()
    }

    // Inicia la navegacion dentro de la actividad
    private fun initNavigation() {
        // Busca y asigna el contenedor de fragments a navHost
        val navHost =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        // Le asigna un contenedor al controller
        navController = navHost.navController
        // Le asigna un controlador al bottomNav
        binding.bottomNavView.setupWithNavController(navController)
    }

}