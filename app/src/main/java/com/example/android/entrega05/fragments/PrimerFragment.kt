package com.example.android.entrega05.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.entrega05.R
import kotlinx.android.synthetic.main.fragment_primer.*

/**
 * Clase fragmen que configura la layout y se comunica con el otro fragment con la información dada por el usuario
 */
class PrimerFragment : Fragment() {

    var activityListener: FragmentListener? = null


    /**
     * Inflo el fragment
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_primer, container, false)
    }


    /**
     * Establezco el listener y el traspaso de información
     */
    override fun onStart() {
        super.onStart()
        Log.i("TEST", "${edittext.text}")

        //LIstener del boton
        button_calcu.setOnClickListener {
            var pond = 1.0
            when {
                radioButton1.isChecked -> pond = 4.5
                radioButton2.isChecked -> pond = 4.3
                radioButton3.isChecked -> pond = 3.7
                radioButton4.isChecked -> pond = 2.8
                radioButton5.isChecked -> pond = 1.2
            }
            Log.i("TEST", "${edittext.text}")

            if (grupo.checkedRadioButtonId != -1 && !TextUtils.isEmpty(edittext.text)) {

                var final = Integer.parseInt(edittext.text.toString()) * pond

                //Envio datos a traves del Listener creado
                activityListener?.pasaDatos(final.toInt())
            }

        }
    }

    /**
     * Interfaz con la que envio informacion a la Activity
     */
    interface FragmentListener {

        fun pasaDatos(area: Int)
    }


}
