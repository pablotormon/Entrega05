package com.example.android.entrega05

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.android.entrega05.fragments.PrimerFragment
import com.example.android.entrega05.fragments.SegundoFragment

/**
 * Actividad donde se inflan los dros fragments y que implementa la interfaz Fragmnt Listener para comunicarse entre fragments
 */
class CalculatorActivity : AppCompatActivity(), PrimerFragment.FragmentListener {

    private var inputFragment: PrimerFragment? = null
    private var outputFragment: SegundoFragment? = null

    /**
     * Inflo la layout de la activity y creo los ragments y los paso al suportfragmentmanager
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        inputFragment = PrimerFragment()
        inputFragment?.activityListener = this
        outputFragment = SegundoFragment()


        supportFragmentManager.beginTransaction().add(R.id.layout_top, inputFragment!!).commit()
        supportFragmentManager.beginTransaction().add(R.id.layout_bottom, outputFragment!!).commit()
    }

    /**
     * Sobreescribo la función para poder pasar la información al egundo fragment
     */
    override fun pasaDatos(area: Int) {
        outputFragment?.dameDatos(area)
    }

}
