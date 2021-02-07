package com.andreyplis.recipecounter.model

import androidx.room.*
import com.andreyplis.recipecounter.db.entity.*


interface Recipe
{
    @TypeConverters(RecipeEntity.RecipeTypeConverter::class)
    enum class TYPE {
        DESERT,
        CAKE
    }

    val id: Int?
    val description: String
    val type: TYPE
    val count: Int
    val weight: Int
    val diameter: Int
    val price: Float
}