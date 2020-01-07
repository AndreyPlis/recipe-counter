package com.andreyplis.recipecounter.db.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.andreyplis.recipecounter.model.Measure


@Entity(tableName = "measures")
data class MeasureEntity(
    @PrimaryKey(autoGenerate = true)
    override val id: Int,
    override val measure: String
) : Measure, Parcelable {

    override fun toString(): String {
        return measure
    }

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(measure)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MeasureEntity> {
        override fun createFromParcel(parcel: Parcel): MeasureEntity {
            return MeasureEntity(parcel)
        }

        override fun newArray(size: Int): Array<MeasureEntity?> {
            return arrayOfNulls(size)
        }
    }
}