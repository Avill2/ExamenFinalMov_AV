package com.example.andreavillacis.av_exammoviles_iib.Conductor

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.Button
import android.widget.TextView
import com.example.andreavillacis.av_exammoviles_iib.Detalle.DetallesConductorClienteActivity
import com.example.andreavillacis.av_exammoviles_iib.EntidadesParcelable.Conductor
import com.example.daro.carritocompras.R

class ConductorClienteAdapter(private val conductorList: List<Conductor>) :  RecyclerView.Adapter<ConductorClienteAdapter.MyViewHolder>(){

    private var position: Int = 0

    fun getPosition(): Int {
        return position
    }

    fun setPosition(position: Int) {
        this.position = position
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnCreateContextMenuListener {

        var nombre: TextView
        var direccion : TextView
        var fechaApertura: TextView
        var detalles: Button

        lateinit var conductor: Conductor

        init {
            nombre = view.findViewById(R.id.txtNombreTienda) as TextView
            direccion = view.findViewById(R.id.txtDireccionTienda) as TextView
            fechaApertura = view.findViewById(R.id.txtFechaAperturaTienda) as TextView
            detalles = view.findViewById(R.id.btnDetalles) as Button
            view.setOnCreateContextMenuListener(this)
        }

        override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
            /*menu?.add(Menu.NONE, R.id.item_menu_compartir, Menu.NONE, R.string.menu_share)*/
            //menu?.add(Menu.NONE, R.id.item_menu_editar, Menu.NONE, "Editar")
            //menu?.add(Menu.NONE, R.id.item_menu_eliminar, Menu.NONE, "Eliminar")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_conductor_layout, parent, false)

        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val tienda = conductorList[position]
        holder.nombre.text = tienda.nombre
        holder.direccion.text = tienda.apellido
        holder.fechaApertura.text = tienda.fechaNacimiento
        holder.conductor = tienda
        holder.detalles.setOnClickListener{
            v: View ->
            val intent = Intent(v.context, DetallesConductorClienteActivity::class.java)
            intent.putExtra("detallesConductorCliente", tienda)

            v.context.startActivity(intent)
        }
        holder.itemView.setOnLongClickListener {
            setPosition(holder.adapterPosition)
            false
        }
    }

    override fun getItemCount(): Int {
        return conductorList.size
    }


}