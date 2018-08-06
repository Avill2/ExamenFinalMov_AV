package com.example.andreavillacis.av_exammoviles_iib.EntidadesParcelable

import android.os.Parcel
import android.os.Parcelable

class Auto(var id: Int,
           var chasis: Int,
           var nombre: String,
           var nombreModelo: String,
           var precio: String,
           var color: String,
           var anio: Int,
           var imagenAuto:String,
           var conductorId:Int,
           var createdAt: Long,
           var updatedAt: Long) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readLong(),
            parcel.readLong()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(chasis)
        parcel.writeString(nombre)
        parcel.writeString(nombreModelo)
        parcel.writeString(precio)
        parcel.writeString(color)
        parcel.writeInt(anio)
        parcel.writeString(imagenAuto)
        parcel.writeInt(conductorId)
        parcel.writeLong(createdAt)
        parcel.writeLong(updatedAt)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Auto> {
        override fun createFromParcel(parcel: Parcel): Auto {
            return Auto(parcel)
        }

        override fun newArray(size: Int): Array<Auto?> {
            return arrayOfNulls(size)
        }
    }
}
