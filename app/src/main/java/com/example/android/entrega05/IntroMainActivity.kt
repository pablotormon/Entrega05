package com.example.android.entrega05

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_intro_main.*

/**
 * Clase Main donde simplemente se visualiza una barra de cargando
 */
class IntroMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Ingflo la layout
        setContentView(R.layout.activity_intro_main)

        //Genero la animación
        progressBarAnimation()
    }

    /**
     * Función donde simulo que la pagina principal se está cargando usando una Progress Bar
     */
    private fun progressBarAnimation() {

        intro_PG.visibility = View.VISIBLE

        var progressStatus: Int = 0
        Thread(Runnable {
            while (progressStatus < 100) {
                try {
                    progressStatus += 5
                    Thread.sleep(100)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

                intro_PG.progress = progressStatus
            }

            intro_PG.visibility = View.INVISIBLE
            irAMainActivity()
        }).start()

    }

    /**
     * Función que me envia a la MainActivity  y cierra la Introducción
     */
    private fun irAMainActivity() {

        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
