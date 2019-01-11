package com.example.android.entrega05.model

import android.content.Intent
import android.content.res.Resources
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.android.entrega05.ProfileActivity
import com.example.android.entrega05.R

class IslandViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindItem(isla: Island, nightMode: Boolean) {


        //Relaciono la informacion del objeto con cada itemview

        var nombre: TextView = itemView.findViewById(R.id.card_title) as TextView
        var location: TextView = itemView.findViewById(R.id.card_location) as TextView
        var size: TextView = itemView.findViewById(R.id.card_size) as TextView
        var price: TextView = itemView.findViewById(R.id.card_price) as TextView
        var imagen: ImageView = itemView.findViewById(R.id.card_image)

        nombre.text = isla.Name
        location.text = isla.Location
        size.text = "${isla.Size.toString()} ACRES"
        price.text ="${isla.Price.toString()}M$"
        imagen.setImageResource(isla.Image!!)


        //En funcion del modo noche o normal, ajusto algunos colores del cardview

        if(nightMode){
            val cardview: CardView = itemView.findViewById(R.id.cardview)
            cardview.setCardBackgroundColor(itemView.context.resources.getColor(R.color.colorHardGrey))
            nombre.setTextColor(itemView.context.resources.getColor(R.color.colorWhite))
            size.setTextColor(itemView.context.resources.getColor(R.color.colorWhite))
            location.setTextColor(itemView.context.resources.getColor(R.color.colorWhite))
        }else{
            val cardview: CardView = itemView.findViewById(R.id.cardview)
            cardview.setCardBackgroundColor(itemView.context.resources.getColor(R.color.colorWhite))
            nombre.setTextColor(itemView.context.resources.getColor(R.color.colorBlack))
            size.setTextColor(itemView.context.resources.getColor(R.color.colorHardGrey))
            location.setTextColor(itemView.context.resources.getColor(R.color.colorHardGrey))

        }


        //Añado un listener a cada Cardview

        itemView.setOnClickListener {

            var formIntent = Intent(itemView.context, ProfileActivity::class.java)
            formIntent.putExtra("nombreIsla", isla.Name.toString())
            formIntent.putExtra("locationIsla", isla.Location.toString())
            formIntent.putExtra("precioIsla", isla.Price.toString())
            formIntent.putExtra("sizeIsla", isla.Size.toString())
            formIntent.putExtra("imagenIsla", isla.Image!!)
            itemView.context.startActivity(formIntent)

        }




    }


}