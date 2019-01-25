package com.example.android.entrega05

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth

/**
 * Clase que se ejecuta al inicio para gestionar el registro del ususario
 */
class LoginActivity : AppCompatActivity() {

    private val REQUEST_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Inflo la layout vacia
        setContentView(R.layout.activity_login)

        //Creo la variable mAuth con el user
        val mAuth = FirebaseAuth.getInstance()

        //Compruebo si el usuario esta ya loggeado y procedo
        signedIn(mAuth)
    }

    /**
     * Función que comprueba si el usuario se ha registrado ya, en ese caso pasamos a MainActivity, sino ejecutamos authenticateUser()
     */
    private fun signedIn(firebaseAuth: FirebaseAuth) {

        if (firebaseAuth.currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            authenticateUser()
        }
    }

    /**
     * Abrimos una ActivityForResult para hacer el regsitro, usando la libreria FirebaseUI se crea un Intent con el diseño de nuestra App
     */
    private fun authenticateUser() {

        startActivityForResult(
            AuthUI.getInstance().createSignInIntentBuilder()
                .setTheme(R.style.AppTheme)
                .setLogo(R.drawable.logofinal)
                .setAvailableProviders(obtenerListaProveedores())
                .setIsSmartLockEnabled(false)
                .build(),
            REQUEST_CODE
        )
    }

    /**
     * Funcion que devuelve una lista de los proveedores que queremos implantar
     */
    private fun obtenerListaProveedores(): List<AuthUI.IdpConfig> {

        val providers = ArrayList<AuthUI.IdpConfig>()

        providers.add(AuthUI.IdpConfig.EmailBuilder().build())
        providers.add(AuthUI.IdpConfig.GoogleBuilder().build())

        return providers
    }

    /**
     * Función que se ejecuta post ActivityForResult, en función del codigo que recibimos, vamos a la MainActivity o ejecutamos otras opciones
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val response = IdpResponse.fromResultIntent(data)

        if (requestCode == REQUEST_CODE) {

            if (resultCode == Activity.RESULT_OK) {
                startActivity(Intent(this, MainActivity::class.java))
                return
            }
        } else {
            if (response == null) {
                // User cancelled Sign-in
                return
            }

            if (response.error?.errorCode == ErrorCodes.NO_NETWORK) {
                // Device has no network connection
                Toast.makeText(this, "Sin conexión a internet", Toast.LENGTH_LONG).show()
                return
            }

            if (response.error?.errorCode == ErrorCodes.UNKNOWN_ERROR) {
                // Unknown error occurred
                Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
                return
            }
        }
    }
}