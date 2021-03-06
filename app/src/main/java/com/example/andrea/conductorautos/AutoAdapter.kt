package com.example.andreavillacis.av_exammoviles_iib.Auto

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.Button
import android.widget.TextView
import com.example.andreavillacis.av_exammoviles_iib.Detalle.DetallesAutoActivity
import com.example.andreavillacis.av_exammoviles_iib.EntidadesParcelable.Auto
import com.example.andrea.conductorautos.R

class AutoAdapter(private val autoList: List<Auto>) :  RecyclerView.Adapter<AutoAdapter.MyViewHolder>(){

    private var position: Int = 0

    fun getPosition(): Int {
        return position
    }

    fun setPosition(position: Int) {
        this.position = position
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnCreateContextMenuListener {

        var nombres: TextView
        var descripcion : TextView
        var precio: TextView
        var detalles: Button

        lateinit var auto: Auto

        init {
            nombres = view.findViewById(R.id.txtNombreTienda) as TextView
            descripcion = view.findViewById(R.id.txtDireccionTienda) as TextView
            precio = view.findViewById(R.id.txtFechaAperturaTienda) as TextView
            detalles = view.findViewById(R.id.btnDetalles) as Button
            view.setOnCreateContextMenuListener(this)
        }

        override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
            /*menu?.add(Menu.NONE, R.id.item_menu_compartir, Menu.NONE, R.string.menu_share)*/
             menu?.add(Menu.NONE, R.id.item_menu_editar, Menu.NONE, "Editar")
             menu?.add(Menu.NONE, R.id.item_menu_eliminar, Menu.NONE, "Eliminar")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_conductor_layout, parent, false)

        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val productoN = autoList[position]
        holder.nombres.text = productoN.nombre
        holder.descripcion.text = productoN.nombreModelo
        holder.precio.text = productoN.precio
        holder.auto = productoN
        holder.detalles.setOnClickListener{
            v: View ->
            val intent = Intent(v.context, DetallesAutoActivity::class.java)
            intent.putExtra("detallesAuto", productoN)
            v.context.startActivity(intent)
        }
        holder.itemView.setOnLongClickListener {
            setPosition(holder.adapterPosition)
            false
        }
    }

    override fun getItemCount(): Int {
        return autoList.size
    }


}