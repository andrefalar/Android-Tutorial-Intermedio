package com.andrefalar.horoscapp.ui.luck

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.view.isVisible
import com.andrefalar.horoscapp.R
import com.andrefalar.horoscapp.databinding.FragmentLuckBinding
import com.andrefalar.horoscapp.ui.providers.RandomCardProvider
import dagger.hilt.android.AndroidEntryPoint
import java.util.Random
import javax.inject.Inject


@AndroidEntryPoint
class LuckFragment : Fragment() {

    private var _binding: FragmentLuckBinding? = null
    private val binding get() = _binding!!

    //Injectamos el provider para la informacion de las cartas
    @Inject
    lateinit var  randomCardProvider: RandomCardProvider

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        preparePrediction()
        initListeners()
    }

    // configura la informacion de la "prediccion"
    private fun preparePrediction() {
        // llama a mi provider junto a la funcion que genera la prediccion
        val luck = randomCardProvider.getLucky()
        // Esto se pone porque estamos seguros que no sera nulo
        luck?.let {itLuck ->
            val predictionTxt = getString(itLuck.text)
            binding.tvLucky.text = predictionTxt
            binding.ivLuckyCard.setImageResource(itLuck.image)
            binding.tvShare.setOnClickListener { shareResult(predictionTxt) }
        }
    }

    // Definición de una función privada llamada 'shareResult' que toma un argumento 'prediction' de tipo String.
    private fun shareResult(prediction:String) {

        // Creación de un Intent para enviar datos.
        val sendIntent: Intent = Intent().apply {
            // Configura la acción del Intent como una acción de envío.
            action = Intent.ACTION_SEND
            // Agrega el texto (prediction) al Intent como un extra con la clave 'Intent.EXTRA_TEXT'.
            putExtra(Intent.EXTRA_TEXT, prediction)
            // Define el tipo de contenido que se va a enviar, en este caso texto plano.
            type = "text/plain"
        }

        // Crea un Intent de tipo chooser (selector) que permite al usuario elegir una aplicación para compartir.
        val shareIntent = Intent.createChooser(sendIntent, null)
        // Inicia una actividad con el Intent de compartir.
        startActivity(shareIntent)
    }


    private fun initListeners() {
            binding.ivRoulette.setOnClickListener { spinRoulette() }
    }

    // Inicia la animacion de la ruleta
    private fun spinRoulette() {

        // Permite generar numeros aleatorios
        val random = Random()
        // Genera los grados girar de maneta aleatoria
        val degrees = random.nextInt(1440) + 360
        // Declaramos un ObjectAnimator
        val animator = ObjectAnimator.ofFloat(binding.ivRoulette, View.ROTATION, 0f, degrees.toFloat())
        // Duracion de la animacion en milisegundos
        animator.duration = 2000
        // Establece que la animacion se relentice al terminar
        animator.interpolator = DecelerateInterpolator()
        // Al terminar esta animacion inicia la siguiente lambda (slideCard)
        animator.doOnEnd { slideCard() }
        // Inicia la animacion
        animator.start()
    }

    // Inicia la animacion de deslizar la carta hacia arriba
    private fun slideCard(){
        // Carga la animacion de XML
        val slideUpAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_up)

        // Configura la etapa de la animacion
        slideUpAnimation.setAnimationListener(object: Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {
                binding.ivCardReverse.isVisible = true
            }

            // Indica que debe hacer cuando termine la animacion (la encadena con otra)
            override fun onAnimationEnd(animation: Animation?) {
                growUp()
            }

            override fun onAnimationRepeat(animation: Animation?) {}

        })

        // Ejecuta la animacion
        binding.ivCardReverse.startAnimation(slideUpAnimation)
    }

    // Inicia animacion de agrandar la carta
    private fun growUp() {
        val growAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.grow_up)

        growAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}

            // Indica que debe hacer cuando termine la animacion
            override fun onAnimationEnd(animation: Animation?) {
                binding.ivCardReverse.isVisible = false
                showPremonitionView()
            }

            override fun onAnimationRepeat(animation: Animation?) {}

        })

        // Ejcuta la animacion
        binding.ivCardReverse.startAnimation(growAnimation)
    }

    // Animacion de la transicion hacia la prediccion
    private fun showPremonitionView() {
        val disappearAnimation = AlphaAnimation(1.0f, 0.0f)
        disappearAnimation.duration = 200

        val appearAnimation = AlphaAnimation(0.0f, 1.0f)
        appearAnimation.duration = 1000

        disappearAnimation.setAnimationListener(object: Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {}

            // Indica que debe hacer cuando termine la animacion
            override fun onAnimationEnd(animation: Animation?) {
                binding.preview.isVisible = false
                binding.prediction.isVisible  = true
            }

            override fun onAnimationRepeat(animation: Animation?) {}

        })

        // ejecuta la animacion
        binding.preview.startAnimation(disappearAnimation)
        binding.prediction.startAnimation(appearAnimation)


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLuckBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}