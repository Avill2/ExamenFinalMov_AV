package com.example.andreavillacis.av_exammoviles_iib.Detalle

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.view.View
import android.widget.Toast
import com.example.andreavillacis.av_exammoviles_iib.EntidadesParcelable.Auto
import com.example.andrea.conductorautos.R
import kotlinx.android.synthetic.main.activity_detalles_auto.*


class DetallesAutoActivity : AppCompatActivity() {

    var auto: Auto? = null
    lateinit var myBitmapAgain:Bitmap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_auto)

        auto = intent.getParcelableExtra("detallesPokemon")


        txtShowNumeroProd.text = auto?.chasis.toString()
        txtShowNombreP.text = auto?.nombre
        txtShowDescrip.text = auto?.nombreModelo
        txtShowPrecio.text = auto?.precio
        txtShowFechaL.text = auto?.color
        txtShowGar.text = auto?.anio.toString()

        myBitmapAgain = decodeBase64(auto?.imagenAuto.toString()!!)
        showImageViewPokemon.setImageBitmap(myBitmapAgain)

    }

    fun decodeBase64(input: String): Bitmap {
        val decodedBytes =  Base64.decode(input,0)
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }


}
