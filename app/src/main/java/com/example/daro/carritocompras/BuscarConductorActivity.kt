package com.example.daro.carritocompras

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.example.andreavillacis.av_exammoviles_iib.Conductor.ConductorClienteAdapter
import com.example.andreavillacis.av_exammoviles_iib.EntidadesParcelable.Conductor
import com.example.andreavillacis.av_exammoviles_iib.database.DatabaseConductor
import kotlinx.android.synthetic.main.activity_buscar_conductor.*

class BuscarConductorActivity : AppCompatActivity() {

    lateinit var adaptador: ConductorClienteAdapter
    lateinit var conductors: ArrayList<Conductor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar_conductor)


        btnBuscarTiendaServer.setOnClickListener { v: View? ->
            consultarDatos()
        }


    }

    fun consultarDatos(){
        if (txtBuscarTienda.equals("")){
            Toast.makeText(this,"Ingrese parametro de busqueda",Toast.LENGTH_SHORT).show()
        }else{
            var datoBusqueda:String = txtBuscarTienda.text.toString()
            

            conductors = DatabaseConductor.buscarConductor(datoBusqueda)

            //Toast.makeText(this,datoBusqueda,Toast.LENGTH_SHORT).show()

            val layoutManager = LinearLayoutManager(this)
            adaptador = ConductorClienteAdapter(conductors)
            recyclerView_Resultados_Entrenador.layoutManager = layoutManager
            recyclerView_Resultados_Entrenador.itemAnimator = DefaultItemAnimator()
            recyclerView_Resultados_Entrenador.adapter = adaptador
            adaptador.notifyDataSetChanged()

            registerForContextMenu(recyclerView_Resultados_Entrenador)

        }
    }
}
