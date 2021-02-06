package com.andreyplis.recipecounter.db.entity

import android.os.*
import androidx.room.*
import com.andreyplis.recipecounter.model.*


@Entity(tableName = "goods")
data class GoodEntity(
    @PrimaryKey(autoGenerate = true)
    override val id: Int = 0,
    override val name: String,
    override val measure: Int,
    override val count: Int,
    override val price: Float
) : Good, Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readInt(),
        parcel.readFloat()
    ) {
    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(name)
        parcel.writeInt(measure)
        parcel.writeInt(count)
        parcel.writeFloat(price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GoodEntity> {
        override fun createFromParcel(parcel: Parcel): GoodEntity {
            return GoodEntity(parcel)
        }

        override fun newArray(size: Int): Array<GoodEntity?> {
            return arrayOfNulls(size)
        }
    }
}