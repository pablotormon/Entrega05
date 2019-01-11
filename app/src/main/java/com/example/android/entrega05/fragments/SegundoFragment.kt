package com.example.android.entrega05.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.android.entrega05.R
import kotlinx.android.synthetic.main.fragment_segundok.*


/**
 * Clase fragment que muestra el valor de la isla
 */
class SegundoFragment : Fragment() {

    /**
     * Inflo la layout delmsegundo fragment
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_segundok, container, false)
    }

    /**
     * Funcion que recibe los datos de Activity y los pasa al textview
     */
    fun dameDatos(datos: Int) {
        precio_final.text = "${datos.toString()}M€"
    }
}
