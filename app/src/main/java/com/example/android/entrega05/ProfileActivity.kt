package com.example.android.entrega05

import android.content.Context
import android.content.res.Configuration
import android.gesture.GestureLibraries
import android.gesture.GestureLibrary
import android.gesture.GestureOverlayView.OnGesturePerformedListener
import android.gesture.Prediction
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.transition.TransitionManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.SeekBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_profile.*


/**
 * Clase perfil donde dispongo la información de la isla tocada y permito hacer una oferta, ademas con un  gesto puedes obtener un descuento
 */
class ProfileActivity : AppCompatActivity() {

    private var gestureLibrary: GestureLibrary? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflo la layout principal
        setContentView(R.layout.activity_profile)

        //Relleno la información del Intent pasado
        rellenarInformacion()

        //Conecta la Seekbar con el valor a ofertar
        conexionSeekBar()

        //Añade listeners a los dos botoners
        setListeners()

        //Añade gestos a la layout general y le da una función
        anadirGestos()

    }


    /**
     * Sobrescribo la funcion para detectar cuando gira el telefono y reducir la imagen para que se vean la layout
     */
    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)

        Log.i("xaxa", "${profile_image.layoutParams.height}")

        // Edito laa imagen con animación en función de si esta horizontal o vertical
        if (newConfig?.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            TransitionManager.beginDelayedTransition(container)
            profile_image.layoutParams.height = 100
        } else if (newConfig?.orientation == Configuration.ORIENTATION_PORTRAIT) {
            TransitionManager.beginDelayedTransition(container)
            profile_image.layoutParams.height = -2
        }
    }

    /**
     * Función que rellena los campos del perfil de la isla desde la base de datos
     */
    fun rellenarInformacion() {

        profile_title.text = intent.getStringExtra("nombreIsla")
        profile_location.text = intent.getStringExtra("locationIsla")
        profile_size.text = intent.getStringExtra("sizeIsla") + " ACRES"
        profile_price.text = intent.getStringExtra("precioIsla") + "M$"

        profile_image.setImageResource(intent.getIntExtra("imagenIsla", R.drawable.almarjan))

    }


    /**
     * Función que relaciona el estado de la seekbar con el textview
     */
    private fun conexionSeekBar() {

        profile_seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                profile_oferta.text = progress.toString() + "M$"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }


    /**
     * Función que establece los listeners en cada boton del perfil
     */
    private fun setListeners() {

        profile_button.setOnClickListener {

            //Cierro la activity
            finish()

            //Creo un shared prefence donde guardo el nombre de la islas ofertadas
            val editor = getSharedPreferences("ofertas", Context.MODE_PRIVATE)
            var set = editor.getStringSet("ofertas", mutableSetOf())
            set!!.add(intent.getStringExtra("nombreIsla"))

            editor.edit().putStringSet("ofertas", set).commit()

            Toast.makeText(this, "Tu oferta ha sido enviada correctamente", Toast.LENGTH_LONG).show()
        }

        profile_button_2.setOnClickListener {

            var snack = Snackbar.make(profile_CL, "Vas a cancelar la oferta", Snackbar.LENGTH_LONG)
            snack.setAction("CANCELAR") {
                finish()
            }
            snack.show()
        }

        profile_image.setOnClickListener {
            TransitionManager.beginDelayedTransition(container)
            profile_image.layoutParams.height = 100
            profile_image.requestLayout()
        }

    }


    /**
     * Funcion que configura el gestor de gestos con el ejemplo extraido de Gesture Builder y le da una función que hacer
     */
    private fun anadirGestos() {

        var gestureOverlayView = gesture_detector
        var gesturePerformedListener: OnGesturePerformedListener =
            OnGesturePerformedListener { _, gesture ->
                val prediction: ArrayList<Prediction> = gestureLibrary!!.recognize(gesture)
                if (prediction.size > 0) {
                    Toast.makeText(this, "Has activado el descuento secreto!!", Toast.LENGTH_LONG).show()
                    profile_price.text = "${intent.getStringExtra("precioIsla").toInt() * 0.8}M$"
                }
            }

        gestureLibrary = GestureLibraries.fromRawResource(this, R.raw.gesture)
        gestureLibrary!!.load()
        gestureOverlayView.addOnGesturePerformedListener(gesturePerformedListener)
    }


}


