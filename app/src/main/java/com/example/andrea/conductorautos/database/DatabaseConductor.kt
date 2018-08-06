package com.example.andreavillacis.av_exammoviles_iib.database

import android.os.StrictMode
import android.util.Log
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.example.andreavillacis.av_exammoviles_iib.EntidadesParcelable.Conductor
import com.github.kittinunf.fuel.*

class DatabaseConductor{

    companion object {

        fun insertarConductor(conductor: Conductor){
            "http://172.29.64.47:1337/Conductor".httpPost(listOf("nombre" to conductor.nombre, "apellido" to conductor.apellido, "fechaNacimiento" to conductor.fechaNacimiento, "numeroAutos" to conductor.numeroAutos, "licenciaValida" to conductor.licenciaValida))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun eliminarConductor(id: Int) {
            "http://172.29.64.47:1337/Conductor/$id".httpDelete()
                    .responseString { request, response, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun actualizaConductor(conductor: Conductor) {
            "http://172.29.64.47:1337/Conductor/${conductor.id}".httpPut(listOf("nombre" to conductor.nombre, "apellido" to conductor.apellido, "fechaNacimiento" to conductor.fechaNacimiento, "numeroAutos" to conductor.numeroAutos, "licenciaValida" to conductor.licenciaValida))
                    .responseString { request, _, result ->
                        Log.d("http-ejemplo", request.toString())
                    }
        }

        fun getList(): ArrayList<Conductor> {
            val chofer: ArrayList<Conductor> = ArrayList()
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val (request, response, result) = "http://172.29.64.47:1337/Conductor".httpGet().responseString()
            val jsonStringAutor = result.get()

            val parser = Parser()
            val stringBuilder = StringBuilder(jsonStringAutor)
            val array = parser.parse(stringBuilder) as JsonArray<JsonObject>

            array.forEach {
                val id = it["id"] as Int
                val nombre = it["nombre"] as String
                val apellio = it["apellido"] as String
                val fechaNacimiento = it["fechaNacimiento"] as String
                val numeroAutos = it["numeroAutos"] as Int
                val licencia = it["licenciaValida"] as Int
                val conductores = Conductor(id, nombre, apellio, fechaNacimiento, numeroAutos, licencia, 0, 0)
                chofer.add(conductores)
            }
            return chofer
        }

        fun buscarConductor(nombre:String): ArrayList<Conductor> {
            val chofer: ArrayList<Conductor> = ArrayList()
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val (request, response, result) = "http://172.29.64.47:1337/Conductor?nombre=${nombre}".httpGet().responseString()
            val jsonStringAutor = result.get()

            val parser = Parser()
            val stringBuilder = StringBuilder(jsonStringAutor)
            val array = parser.parse(stringBuilder) as JsonArray<JsonObject>

            array.forEach {
                val id = it["id"] as Int
                val nombre = it["nombre"] as String
                val apellido = it["apellido"] as String
                val fechanacimiento = it[ "fechaNacimiento"] as String
                val numeroauts= it["numeroAutos"] as Int
                val licencia = it["licenciaValida"] as Int
                val conductores = Conductor(id, nombre, apellido, fechanacimiento, numeroauts, licencia, 0, 0)
                chofer.add(conductores)
            }
            return chofer
        }

    }
}