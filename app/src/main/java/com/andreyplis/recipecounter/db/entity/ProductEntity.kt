package com.andreyplis.recipecounter.db.entity

import android.os.*
import androidx.room.*
import com.andreyplis.recipecounter.model.*


@Entity(
    tableName = "products",
    indices = [Index(value = arrayOf("measureId"))],
    foreignKeys = [ForeignKey(
        entity = MeasureEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("measureId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    override val id: Int = 0,
    override val name: String,
    override val measureId: Int,
    override val count: Int,
    override val price: Float
) : Product, Parcelable {
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
        parcel.writeInt(measureId)
        parcel.writeInt(count)
        parcel.writeFloat(price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductEntity> {
        override fun createFromParcel(parcel: Parcel): ProductEntity {
            return ProductEntity(parcel)
        }

        override fun newArray(size: Int): Array<ProductEntity?> {
            return arrayOfNulls(size)
        }
    }
}