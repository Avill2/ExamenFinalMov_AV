package com.example.andreavillacis.av_exammoviles_iib.database

import android.os.StrictMode
import android.util.Log
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.example.andreavillacis.av_exammoviles_iib.EntidadesParcelable.Auto
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut

class DatabaseAuto{
    companion object {

        fun insertarAuto(auto: Auto){
            "http://172.29.64.47:1337/Auto".httpPost(listOf("chasis" to auto.chasis, "nombre" to auto.nombre, "nombreModelo" to auto.nombreModelo, "precio" to auto.precio, "color" to auto.color,"anio" to auto.anio,"imagenAuto" to auto.imagenAuto,"conductorId" to auto.conductorId ))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun eliminarAuto(id: Int) {
            "http://172.29.64.47:1337/Auto/$id".httpDelete()
                    .responseString { request, response, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun actualizaAuto(auto: Auto) {
            "http://172.29.64.47:1337/Auto/${auto.id}".httpPut(listOf("chasis" to auto.chasis, "nombre" to auto.nombre, "nombreModelo" to auto.nombreModelo, "precio" to auto.precio, "color" to auto.color, "anio" to auto.anio))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun getAutoList(conductorId: Int): ArrayList<Auto> {
            val auto: ArrayList<Auto> = ArrayList()
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val (request, response, result) = "http://172.29.64.47:1337/Auto?conductorId=$conductorId".httpGet().responseString()
            val jsonStringPokemon = result.get()

            val parser = Parser()
            val stringBuilder = StringBuilder(jsonStringPokemon)
            val array = parser.parse(stringBuilder) as JsonArray<JsonObject>

            array.forEach {
                val id = it["id"] as Int
                val chasis = it["chasis"] as Int
                val nombre = it["nombre"] as String
                val modelo = it["nombreModelo"] as String
                val precio = it["precio"] as String
                val color = it["color"] as String
                val anio = it["anio"] as Int
                val imagenAuto = it["imagenAuto"] as String
                //val latitud = it["latitud"] as Double
               // val longitud = it["longitud"] as Double
                val carros = Auto(id, chasis, nombre, modelo, precio, color, anio, imagenAuto, conductorId, 0, 0)
                auto.add(carros)
            }
            return auto
        }




    }
}