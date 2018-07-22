package com.example.andreavillacis.av_exammoviles_iib.Listar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import com.example.andreavillacis.av_exammoviles_iib.Conductor.ConductorClienteAdapter
import com.example.andreavillacis.av_exammoviles_iib.EntidadesParcelable.Conductor
import com.example.daro.carritocompras.R
import com.example.andreavillacis.av_exammoviles_iib.database.DatabaseConductor
import kotlinx.android.synthetic.main.activity_listar_conductor_cliente.*

class ListarConductorClienteActivity : AppCompatActivity() {

    lateinit var adaptador: ConductorClienteAdapter
    lateinit var conductors: ArrayList<Conductor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_conductor_cliente)

        conductors = DatabaseConductor.getList()


        val layoutManager = LinearLayoutManager(this)
        adaptador = ConductorClienteAdapter(conductors)
        recyclerView_listarEntrenadores_Clientes.layoutManager = layoutManager
        recyclerView_listarEntrenadores_Clientes.itemAnimator = DefaultItemAnimator()
        recyclerView_listarEntrenadores_Clientes.adapter = adaptador
        adaptador.notifyDataSetChanged()

        registerForContextMenu(recyclerView_listarEntrenadores_Clientes)
    }
}
