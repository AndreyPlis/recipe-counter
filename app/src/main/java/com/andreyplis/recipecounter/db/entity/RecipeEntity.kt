package com.andreyplis.recipecounter.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.andreyplis.recipecounter.model.Recipe

@Entity(tableName = "recipes")
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    override val id: Int = 0,
    override val name: String,
    override val description: String,
    override val type: Recipe.TYPE,
    override val measureId: Int,
    override val count: Int
) : Recipe {

    companion object RecipeTypeConverter {
        @TypeConverter
        fun fromString(value: String): Recipe.TYPE = Recipe.TYPE.valueOf(value)

        @TypeConverter
        fun toString(value: Recipe.TYPE): String = value.toString()

    }
}
