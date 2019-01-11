package com.example.android.entrega05.data

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.android.entrega05.R
import com.example.android.entrega05.model.Island
import com.example.android.entrega05.model.IslandViewHolder


/**
 * Clase encargada de administrar l abse de datos y tratarla para inflar la layout
 */
class IslandListAdapter(
    private val list: ArrayList<Island>,
    private val context: Context,
    private val nightMode: Boolean
) :RecyclerView.Adapter<IslandViewHolder>() {


    /**
     * INflo la layout de la Cardview
     */
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): IslandViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.island_card_layout, p0, false)
        return IslandViewHolder(view)
    }


    /**
     * Devuelvo el tamaño de la lista de Islas
     */
    override fun getItemCount(): Int {

        return list.size
    }


    /**
     * Funcion que ejecuta bindItem() al reciclar views
     */
    override fun onBindViewHolder(p0: IslandViewHolder, p1: Int) {

        p0.bindItem(list[p1], nightMode)
    }


}