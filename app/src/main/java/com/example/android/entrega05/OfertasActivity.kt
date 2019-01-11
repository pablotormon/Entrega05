package com.example.android.entrega05

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_ofertas.*


/**
 * Clase ofertas, donde dispongo todas las ofertas hechas por el ususario, un common gesture para eliminarlas
 */
class OfertasActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Inflo la layout
        setContentView(R.layout.activity_ofertas)

        //Relleno la listvie con los datos del Shared Preferences
        rellenarLista()

        //Añade un listener al nav icon de la toolbar
        back_ofertas.setOnClickListener { finish() }

        //Añadir gestos al recyclerview
        anadirGestos2()
    }

    /**
     * Funcion que rellena la ListViw con las ofertas guardadas en el Shared Preferences
     */
    private fun rellenarLista() {

        val editor = getSharedPreferences("ofertas", Context.MODE_PRIVATE)
        val islasOfertadas = editor.getStringSet("ofertas", mutableSetOf())
        Log.d("SHAREDPREFERENCES", "${islasOfertadas.size}")

        var ofertasAdapter: ArrayAdapter<String> =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, islasOfertadas.toMutableList())

        ofertas_listview.adapter = ofertasAdapter
    }

    /**
     * Funcion que añade gesto secreto a la tollbar
     */
    private fun anadirGestos2() {

        var gDetector = GestureDetector(this, MiGestureListener(this))
        val touchListener = View.OnTouchListener { _, event ->

            gDetector.onTouchEvent(event)

        }

        info_ofertas.setOnTouchListener(touchListener)
        rellenarLista()


    }

    /**
     * Clase Listener para el logo para eliminar los datos de la lista
     */
    inner class MiGestureListener(var context: Context) : GestureDetector.SimpleOnGestureListener() {

        override fun onDown(e: MotionEvent?): Boolean {
            return true
        }

        override fun onLongPress(e: MotionEvent) {

            //Creo un shared prefence donde guardo el nombre de la islas ofertadas
            val editor2 = context.getSharedPreferences("ofertas", Context.MODE_PRIVATE)
            editor2.edit().putStringSet("ofertas", mutableSetOf<String>()).commit()
            rellenarLista()
            Toast.makeText(context, "Has eliminado correctamente tus ofertas", Toast.LENGTH_LONG).show()
        }

        override fun onSingleTapUp(e: MotionEvent?): Boolean {

            Toast.makeText(context, "Mantener pulsado para eliminar", Toast.LENGTH_LONG).show()
            return true
        }
    }


}
