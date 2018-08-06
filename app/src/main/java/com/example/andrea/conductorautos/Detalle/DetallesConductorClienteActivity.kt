package com.example.andreavillacis.av_exammoviles_iib.Detalle

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.andreavillacis.av_exammoviles_iib.Auto.AutoClienteAdapter
import com.example.andreavillacis.av_exammoviles_iib.EntidadesParcelable.Auto
import com.example.andreavillacis.av_exammoviles_iib.EntidadesParcelable.Conductor

import com.example.andrea.conductorautos.R
import com.example.andreavillacis.av_exammoviles_iib.database.DatabaseAuto
import kotlinx.android.synthetic.main.activity_detalles_conductor.*

class DetallesConductorClienteActivity : AppCompatActivity() {

    var conductor: Conductor? = null
    lateinit var auto: ArrayList<Auto>
    lateinit var adaptador: AutoClienteAdapter
    //lateinit var codigoBotonoActivar:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_conductor_cliente)

        conductor = intent.getParcelableExtra("detallesConductorCliente")
        //codigoBotonoActivar = intent.getStringExtra("codigoBotonoActivar")

        //Toast.makeText(this,codigoBotonoActivar,Toast.LENGTH_SHORT).show()

        txtShowIdTienda.text = conductor?.id.toString()
        txtShowNombreT.text = conductor?.nombre
        txtShowDireccionT.text = conductor?.apellido
        txtShowFechaApertura.text = conductor?.fechaNacimiento
        txtShowNRUCS.text = conductor?.numeroAutos.toString()
        txtShowMat.text = if(conductor?.licenciaValida == 1) "Si" else "No"



        auto = DatabaseAuto.getAutoList(conductor?.id!!)
        Log.d("resultado",auto.toString())

        val layoutManager = LinearLayoutManager(this)
        adaptador = AutoClienteAdapter(auto)
        recycler_view_producto.layoutManager = layoutManager
        recycler_view_producto.itemAnimator = DefaultItemAnimator()
        recycler_view_producto.adapter = adaptador
        adaptador.notifyDataSetChanged()

        registerForContextMenu(recycler_view_producto)
    }
}
