package com.andrefalar.horoscapp.ui.palmistry

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andrefalar.horoscapp.R
import com.andrefalar.horoscapp.databinding.FragmentPalmistryBinding

class PalmistryFragment : Fragment() {

    private var _pinding: FragmentPalmistryBinding? = null
    private val pinding get() =_pinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _pinding = FragmentPalmistryBinding.inflate(layoutInflater, container, false)
        return pinding.root
    }

}