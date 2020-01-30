package com.andreyplis.recipecounter.db.entity

import androidx.room.*
import com.andreyplis.recipecounter.model.*

@Entity(tableName = "recipes")
public data class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    override val id: Int = 0,
    override val name: String,
    override val description: String,
    override val type: Recipe.TYPE,
    override val measureId: Int,
    override val count: Int
) : Recipe {


    object RecipeTypeConverter {
        @JvmStatic
        @TypeConverter
        fun fromString(value: String): Recipe.TYPE = Recipe.TYPE.valueOf(value)

        @TypeConverter
        @JvmStatic
        fun toString(value: Recipe.TYPE): String = value.toString()

    }
}
