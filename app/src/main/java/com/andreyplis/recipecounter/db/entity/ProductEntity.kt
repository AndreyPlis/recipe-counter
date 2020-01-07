package com.andreyplis.recipecounter.db.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.andreyplis.recipecounter.model.Product


@Entity(
    tableName = "products",
    indices = [Index(value = arrayOf("measureId"), unique = true)],
    foreignKeys = [ForeignKey(
        entity = MeasureEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("measureId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    override val id: Long?,
    override val name: String,
    override val measureId: Int,
    override val count: Int,
    override val price: Float
) : Product,Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Long::class.java.classLoader) as? Long,
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