package com.example.andreavillacis.av_exammoviles_iib.Listar

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.MenuItem
import com.example.andreavillacis.av_exammoviles_iib.Conductor.ConductorActivity
import com.example.andreavillacis.av_exammoviles_iib.Conductor.ConductorAdapter
import com.example.andreavillacis.av_exammoviles_iib.EntidadesParcelable.Conductor
import com.example.daro.carritocompras.R

import com.example.andreavillacis.av_exammoviles_iib.database.DatabaseConductor
import kotlinx.android.synthetic.main.activity_listar_conductores.*

class ListarConductoresActivity : AppCompatActivity() {

    lateinit var adaptador: ConductorAdapter
    lateinit var conductors: ArrayList<Conductor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_conductores)

        conductors = DatabaseConductor.getList()


        val layoutManager = LinearLayoutManager(this)
        adaptador = ConductorAdapter(conductors)
        recyclerViewEntrenador.layoutManager = layoutManager
        recyclerViewEntrenador.itemAnimator = DefaultItemAnimator()
        recyclerViewEntrenador.adapter = adaptador
        adaptador.notifyDataSetChanged()

        registerForContextMenu(recyclerViewEntrenador)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var position = adaptador.getPosition()
        var tienda = conductors[position]

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
                val intent = Intent(this, ConductorActivity::class.java)
                intent.putExtra("tipo", "Edit")
                intent.putExtra("conductor", tienda)
                startActivity(intent)
                return true
            }
            R.id.item_menu_eliminar -> {
                val builder = AlertDialog.Builder(this)
                builder.setMessage("Esta seguro de eliminar?")
                        .setPositiveButton("Si", { dialog, which ->
                            DatabaseConductor.eliminarConductor(tienda.id)
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
