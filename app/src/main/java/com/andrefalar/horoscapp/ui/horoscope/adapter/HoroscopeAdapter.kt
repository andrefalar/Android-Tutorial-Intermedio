package com.andrefalar.horoscapp.ui.horoscope.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andrefalar.horoscapp.R
import com.andrefalar.horoscapp.domain.model.HoroscopeInfo

/*
Recibe una lista del modelo de datos correspondiente, pero inicia vacia.
Tambien recibe un funcion lambda para saber si el item fue seleccionado o no
 */
class HoroscopeAdapter(
    private var horoscopeList: List<HoroscopeInfo> = emptyList(),
    private val onItemSelected: (HoroscopeInfo) -> Unit
) :
    RecyclerView.Adapter<HoroscopeViewHolder>() {

    // actualiza el listado
    fun updateList(list: List<HoroscopeInfo>) {
        horoscopeList = list
        notifyDataSetChanged()
    }

    // Crea la instancia del ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoroscopeViewHolder {
        return HoroscopeViewHolder(
            // Creamos la vista que recibe como parametro
            LayoutInflater.from(parent.context).inflate(R.layout.item_horoscope, parent, false)
        )
    }

    // Devuelve el tamaño de la lista de datos
    override fun getItemCount() = horoscopeList.size

    // Se encarga de decirle al ViewHolder lo que tiene que pintar
    override fun onBindViewHolder(holder: HoroscopeViewHolder, position: Int) {
        holder.render(horoscopeList[position], onItemSelected)
    }
}