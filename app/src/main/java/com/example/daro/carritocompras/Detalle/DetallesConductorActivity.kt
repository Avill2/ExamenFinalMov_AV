package com.example.andreavillacis.av_exammoviles_iib.Detalle

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.MenuItem
import android.view.View
import com.example.andreavillacis.av_exammoviles_iib.Auto.AutoActivity
import com.example.andreavillacis.av_exammoviles_iib.Auto.AutoAdapter
import com.example.andreavillacis.av_exammoviles_iib.EntidadesParcelable.Auto
import com.example.andreavillacis.av_exammoviles_iib.EntidadesParcelable.Conductor
import com.example.daro.carritocompras.R
import com.example.andreavillacis.av_exammoviles_iib.database.DatabaseAuto
import kotlinx.android.synthetic.main.activity_detalles_conductor.*

class DetallesConductorActivity : AppCompatActivity() {

    var conductor: Conductor? = null
    lateinit var auto: ArrayList<Auto>
    lateinit var adaptador: AutoAdapter
    //lateinit var codigoBotonoActivar:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_conductor)



        conductor = intent.getParcelableExtra("detallesConductor")
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
        adaptador = AutoAdapter(auto)
        recycler_view_producto.layoutManager = layoutManager
        recycler_view_producto.itemAnimator = DefaultItemAnimator()
        recycler_view_producto.adapter = adaptador
        adaptador.notifyDataSetChanged()

        registerForContextMenu(recycler_view_producto)

        btnNuevoPokemon.setOnClickListener { v: View? ->
            irActividdadCrearPokemon()
        }
    }

    fun irActividdadCrearPokemon(){
        val intent = Intent(this, AutoActivity::class.java)
        intent.putExtra("tipo", "Create")
        intent.putExtra("conductorId", conductor?.id!!)
        startActivity(intent)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var position = adaptador.getPosition()
        var producto1 = auto[position]

        when (item.itemId) {
        /*R.id.item_menu_compartir -> {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/html"
            intent.putExtra(Intent.EXTRA_SUBJECT, "${getString(R.string.autor)} - ${getString(R.string.app_name)}")
            intent.putExtra(Intent.EXTRA_TEXT, "${getString(R.string.name)} ${autor.nombres} ${autor.direccion}\n${getString(R.string.numero_libros)} ${autor.RUC}\n${getString(R.string.fecha_nacimiento)} ${autor.fechaApertura}")
            startActivity(intent)
            return true
        }*/
            R.id.item_menu_editar -> {
                val intent = Intent(this, AutoActivity::class.java)
                intent.putExtra("tipo", "Edit")
                intent.putExtra("auto", producto1)
                startActivity(intent)
                return true
            }
            R.id.item_menu_eliminar -> {
                val builder = AlertDialog.Builder(this)
                builder.setMessage("Esta seguro de eliminar?")
                        .setPositiveButton("Si", { dialog, which ->
                            DatabaseAuto.eliminarAuto(producto1.id)
                            finish()
                            startActivity(intent)
                        }
                        )
                        .setNegativeButton("No", null)
                val dialogo = builder.create()
                dialogo.show()
                return true
            }
            else -> {
                Log.i("menu", "Todos los demas")
                return super.onOptionsItemSelected(item)
            }
        }
    }

}
