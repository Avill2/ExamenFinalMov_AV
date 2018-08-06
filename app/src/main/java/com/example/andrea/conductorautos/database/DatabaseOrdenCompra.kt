package com.example.andreavillacis.av_exammoviles_iib.database

import android.os.StrictMode
import android.util.Log
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.example.andreavillacis.av_exammoviles_iib.EntidadesParcelable.OrdenCompra
import com.example.andreavillacis.av_exammoviles_iib.EntidadesParcelable.OrdenDetalles
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost

class DatabaseOrdenCompra{
    companion object {
        fun insertarOrden(ordenCompra: OrdenCompra){
            "http://172.29.64.47:1337/Orden".httpPost(listOf("total" to ordenCompra.cedulaComprador, "fecha" to ordenCompra.sector, "costoEntrega" to ordenCompra.idProducto))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun insertarOrdenDetalles(ordenDetalles: OrdenDetalles){
            "http://172.29.64.47:1337/DetalleOrden".httpPost(listOf("fechaEnvio" to ordenDetalles.fechaEnvio, "precio" to ordenDetalles.precio, "idProducto" to ordenDetalles.idProducto))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }



        fun getOrdenesList(): ArrayList<OrdenCompra> {
            val orden: ArrayList<OrdenCompra> = ArrayList()
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val (request, response, result) = "http://172.29.64.47:1337/Orden".httpGet().responseString()
            val jsonStringPokemon = result.get()

            val parser = Parser()
            val stringBuilder = StringBuilder(jsonStringPokemon)
            val array = parser.parse(stringBuilder) as JsonArray<JsonObject>

            array.forEach {
                val cedulaIdentidad = it["total"] as Int
                val sector = it["fecha"] as String
                val idPokemon = it["costoEntrega"] as Int
                //val latitud = it["latitud"] as Double
                // val longitud = it["longitud"] as Double
                val ordenn = OrdenCompra(0, cedulaIdentidad, sector, idPokemon)
                orden.add(ordenn)
            }
            return orden
        }
    }



}