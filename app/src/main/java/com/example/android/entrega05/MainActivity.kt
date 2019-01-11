package com.example.android.entrega05

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.android.entrega05.data.IslandListAdapter
import com.example.android.entrega05.model.Island
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main_activity_intro.*
import kotlinx.android.synthetic.main.content_main_activity_intro.*


/**
 * Clase Main real, en ella tengo el RecyclerView con su adapter, botones de la barra de mneu con sus acciones
 */
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var adapter: IslandListAdapter? = null
    private var islandList: ArrayList<Island>? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var modoNoche: Boolean = false
    private var modoLista: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Inflo la layout principal
        setContentView(R.layout.activity_main)

        //Creo la lista de Islas y el registro en SharedPreferences para guardar ofertas
        crearBasesDeDatos()

        //Añadir el toolbar con los menus
        setSupportActionBar(toolbar)

        //Inicializo el layout managar y el adapter
        setUpRecyclerView()

        //Relleno la lista de Islas de objetos
        addData()

        //Creo un listener para el FAB button
        anadirListenerAlFAB()

        //Configuro el menu drawer
        configurarElDrawer()

    }

    /**
     * Funcion que en funcion de si el drawer esta abierto decide si cerrar la app o el drawer
     */
    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    /**
     * Funcion infla el menu al ser llamada
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_activity_intro, menu)
        Log.d("TEST", modoLista.toString())
        menu.findItem(R.id.action_modo_lista).setChecked(modoLista)
        menu.findItem(R.id.action_modo_noche).setChecked(modoNoche)
        return true
    }

    /**
     * Establece un listener para cada opción del menu
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_modo_lista -> {

                if (item.isChecked) {
                    modoLista = false
                    setUpRecyclerView()
                    invalidateOptionsMenu()
                } else {
                    modoLista = true
                    setUpRecyclerView()
                    invalidateOptionsMenu()
                }
                return true
            }

            R.id.action_modo_noche -> {

                if (item.isChecked) {
                    modoNoche = false
                    modoNoche(false)
                    invalidateOptionsMenu()

                } else {
                    modoNoche = true
                    modoNoche(true)
                    invalidateOptionsMenu()

                }
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    /**
     * Establece un listener para cada opción del drawer
     */
    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_mis_islas -> {
                Toast.makeText(this, "Disponible para cuenta premium", Toast.LENGTH_LONG).show()
            }
            R.id.nav_mis_ofertas -> {
                val formIntent = Intent(this, OfertasActivity::class.java)
                this.startActivity(formIntent)

            }
            R.id.nav_calc -> {
                var calcIntent = Intent(this, CalculatorActivity::class.java)
                this.startActivity(calcIntent)
            }
            R.id.nav_ranking -> {
                Toast.makeText(this, "Disponible para cuenta premium", Toast.LENGTH_LONG).show()
            }

        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }


    /**
     * Funcion que inicializa un layout manager y un adapter y los enlaza con el xml
     */
    private fun setUpRecyclerView() {

        if (modoLista) {
            layoutManager = LinearLayoutManager(this)
        } else {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }

        adapter = IslandListAdapter(islandList!!, this, modoNoche)

        content_RV.layoutManager = layoutManager
        content_RV.adapter = adapter
    }

    /**
     * Función que crea una array de elemntos de clase isla para su uso posterior, crea la base de datos
     */
    private fun addData() {

        var islandNameList: Array<String> =
            arrayOf(
                "AL MARJAN",
                "LA ISLA DIAZ",
                "PATROKLOS",
                "RANGYAI",
                "PUMPKIN KEY",
                "APO ISLAND",
                "SPECTABILIS",
                "CAVE CAY",
                "NENGO NENGO ATOLL",
                "KANDOO",
                "SCHULZEWERDER ",
                "VOUVALOS"
            )
        var locationList: Array<String> = arrayOf(
            "Emiratos Árabes",
            "Mexico",
            "Grecia",
            "Tailandia",
            "Estados Unidos",
            "Filipinas",
            "Bahamas",
            "Bahamas",
            "Polinesia Francesa",
            "Maldivas",
            "Polonia",
            "Grecia"
        )
        var sizeList: Array<Int> = arrayOf(115, 2121, 643, 110, 26, 2147, 460, 222, 2223, 42, 21, 32)
        var priceList: Array<Int> = arrayOf(462, 956, 750, 160, 95, 72, 62, 60, 63, 23, 35, 26)
        var imageList: Array<Int> = arrayOf(
            R.drawable.almarjan,
            R.drawable.isladiaz,
            R.drawable.patroklos,
            R.drawable.rangyai,
            R.drawable.pumpkinkey,
            R.drawable.apoisland,
            R.drawable.spectabilis,
            R.drawable.cavecay,
            R.drawable.nengonengoatoll,
            R.drawable.kandoo,
            R.drawable.schulze,
            R.drawable.vouvalos
        )

        for (i in 0..(islandNameList.size - 1)) {

            var isla = Island()
            isla.Name = islandNameList[i]
            isla.Location = locationList[i]
            isla.Size = sizeList[i]
            isla.Price = priceList[i]
            isla.Image = imageList[i]

            islandList!!.add(isla)
        }
        adapter!!.notifyDataSetChanged()

    }

    /**
     * Funcion que crea la lista vacia de objetos ISla y crea el registro en SharedPreferences para guardar las ofertas hechas
     */
    private fun crearBasesDeDatos() {

        /*val editor = getSharedPreferences("ofertas", Context.MODE_PRIVATE).edit()
        val ofertas:MutableSet<String>?= mutableSetOf<String>()
        editor.putStringSet("ofertas",ofertas)
        editor.apply()*/
        islandList = ArrayList<Island>()
    }

    /**
     * Funcion añade un listener al boton FAB que abrira un SnackBar
     */
    private fun anadirListenerAlFAB() {

        custom_fab.setOnClickListener { view ->
            Toast.makeText(this, "Disponible para cuenta premium", Toast.LENGTH_LONG).show()
        }
    }

    /**
     * Funcion que configura el menu deslizante
     */
    private fun configurarElDrawer() {

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    /**
     * Funcion que establece el recycler view en modo noche o lo reestablece
     */
    private fun modoNoche(estado: Boolean) = if (estado) {

        content_RV.setBackgroundColor(Color.BLACK)
        setUpRecyclerView()

    } else {
        content_RV.setBackgroundColor(resources.getColor(R.color.colorLightGrey))
        setUpRecyclerView()
    }


}
