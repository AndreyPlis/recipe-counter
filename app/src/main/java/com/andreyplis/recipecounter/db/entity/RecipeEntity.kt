package com.andreyplis.recipecounter.db.entity

import android.os.*
import androidx.room.*
import com.andreyplis.recipecounter.model.*

@Entity(tableName = "recipes")
public data class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    override val id: Int = 0,
    override val description: String,
    override val type: Recipe.TYPE,
    override val measure: Int,
    override val diameter: Int,
    override val price: Float
) : Recipe, Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        RecipeTypeConverter.fromString(parcel.readString()!!),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readFloat()
    ) {
    }

    object RecipeTypeConverter {
        @JvmStatic
        @TypeConverter
        fun fromString(value: String): Recipe.TYPE = Recipe.TYPE.valueOf(value)

        @TypeConverter
        @JvmStatic
        fun toString(value: Recipe.TYPE): String = value.toString()

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(description)
        parcel.writeString(RecipeTypeConverter.toString(type))
        parcel.writeInt(measure)
        parcel.writeInt(diameter)
        parcel.writeFloat(price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RecipeEntity> {
        override fun createFromParcel(parcel: Parcel): RecipeEntity {
            return RecipeEntity(parcel)
        }

        override fun newArray(size: Int): Array<RecipeEntity?> {
            return arrayOfNulls(size)
        }
    }
}
