package com.example.andreavillacis.av_exammoviles_iib.Conductor

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.andreavillacis.av_exammoviles_iib.EntidadesParcelable.Conductor
import com.example.andreavillacis.av_exammoviles_iib.Listar.ListarConductoresActivity
import com.example.andreavillacis.av_exammoviles_iib.database.DatabaseConductor
import com.example.daro.carritocompras.R
import kotlinx.android.synthetic.main.activity_conductor.*

class ConductorActivity : AppCompatActivity() {

    var conductor: Conductor? = null
    var tipo = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conductor)

        val type = intent.getStringExtra("tipo")

        if (type.equals("Edit")) {
            textViewEntreador.text = "Editar Conductor"
            conductor = intent.getParcelableExtra("conductor")
            fillFields()
            tipo = true
        }

        btnGuardarTienda.setOnClickListener { v: View? ->
            crearTienda()
        }
    }

    fun fillFields() {
        txtNombreTienda.setText(conductor?.nombre)
        txtDireccionTienda.setText(conductor?.apellido)
        txtFechaAperturaTienda.setText(conductor?.fechaNacimiento)
        txtNumeroRuc.setText(conductor?.numeroAutos.toString())
        if (conductor?.licenciaValida == 1) {
            switchMatriz.toggle()
        }
    }

    fun crearTienda(){
        var nombreT = txtNombreTienda.text.toString()
        var direccion = txtDireccionTienda.text.toString()
        var fechaA = txtFechaAperturaTienda.text.toString()
        var RUCs = txtNumeroRuc.text.toString().toInt()
        var matriz = if (switchMatriz.isChecked) 1 else 0


        if (!tipo){

            var tienda = Conductor(0, nombreT, direccion, fechaA, RUCs, matriz, 0, 0)
            DatabaseConductor.insertarConductor(tienda)

        }else{
            var tienda = Conductor(conductor?.id!!, nombreT, direccion, fechaA, RUCs, matriz, 0, 0)
            DatabaseConductor.actualizaConductor(tienda)
        }


        irListarEntrenadorActivity()

    }

    fun irListarEntrenadorActivity(){
        val intent = Intent(this, ListarConductoresActivity::class.java)
        startActivity(intent)
    }
}
