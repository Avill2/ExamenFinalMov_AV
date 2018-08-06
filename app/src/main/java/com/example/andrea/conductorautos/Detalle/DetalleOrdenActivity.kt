package com.example.andreavillacis.av_exammoviles_iib.Detalle

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.andreavillacis.av_exammoviles_iib.EntidadesParcelable.OrdenCompra
import com.example.andreavillacis.av_exammoviles_iib.EntidadesParcelable.OrdenDetalles
import com.example.andrea.conductorautos.R
import com.example.andreavillacis.av_exammoviles_iib.database.DatabaseOrdenCompra
import com.example.andrea.conductorautos.MapsActivity
import com.tapadoo.alerter.Alerter
import kotlinx.android.synthetic.main.activity_datos_comprador.*
import kotlinx.android.synthetic.main.activity_detalle_orden.*


class DetalleOrdenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        var oredenes: OrdenCompra? = null


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_orden)

        oredenes = intent.getParcelableExtra("detallesOrden")

        txtShowCedula.text = oredenes?.cedulaComprador.toString()
        txtShowSector.text = oredenes?.sector
        txtShowIdP.text = oredenes?.idProducto.toString()

        button_mapa.setOnClickListener{v: View? ->
            irActividadMapas()
        }
        btnGuardarDatosOrden.setOnClickListener { v: View? ->
            guardarDatosOrdenDetalles()

        }

    }

    fun irActividadMapas(){
        val intent= Intent(this, MapsActivity::class.java)
        startActivity(intent)
    }



    fun guardarDatosOrdenDetalles(){
        val fechaEnvio = txtFechaEnvio.text.toString()
        val costoProducto = txtPrecioProduc.text.toString().toInt()
        val idProducto = txtShowIdP.text.toString().toInt()
        val ordenDetalles = OrdenDetalles(0, fechaEnvio, costoProducto, idProducto)
        DatabaseOrdenCompra.insertarOrdenDetalles(ordenDetalles)
        Alerter.create(this)
                .setTitle("Orden enviada a CLIENTE")
                .setText("La solicitud fue enviada exitosamente")
                .enableSwipeToDismiss()
                .show()
    }
}
