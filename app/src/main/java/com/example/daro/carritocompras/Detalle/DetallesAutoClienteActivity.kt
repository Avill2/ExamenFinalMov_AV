package com.example.andreavillacis.av_exammoviles_iib.Detalle

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import com.example.daro.carritocompras.DatosCompradorActivity
import com.example.andreavillacis.av_exammoviles_iib.EntidadesParcelable.Auto
import com.example.daro.carritocompras.R
import kotlinx.android.synthetic.main.activity_detalles_producto_cliente.*

class DetallesAutoClienteActivity : AppCompatActivity() {

    var auto: Auto? = null
    lateinit var myBitmapAgain: Bitmap
    lateinit var idProducto:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_producto_cliente)

        auto = intent.getParcelableExtra("detallesAutoCliente")



        txtShowNumeroProd.text = auto?.chasis.toString()
        txtShowNombreProd.text = auto?.nombre
        txtShowDescrip.text = auto?.nombreModelo
        txtShowPrecio.text = auto?.precio
        txtShowFechaLa.text = auto?.color
        txtShowGarantia.text = auto?.anio.toString()

        //Toast.makeText(this,auto?.id.toString(),Toast.LENGTH_SHORT).show()


        myBitmapAgain = decodeBase64(auto?.imagenAuto.toString()!!)
        showImageViewProducto.setImageBitmap(myBitmapAgain)

        btnAdquirirProducto.setOnClickListener { v ->
            irActividadDatosComprador()
        }


    }

    fun decodeBase64(input: String): Bitmap {
        val decodedBytes =  Base64.decode(input,0)
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }

    fun irActividadDatosComprador(){
        val intent = Intent(this, DatosCompradorActivity::class.java)
        idProducto = auto?.id.toString()
        intent.putExtra("idProducto",idProducto)
        startActivity(intent)
    }


}
