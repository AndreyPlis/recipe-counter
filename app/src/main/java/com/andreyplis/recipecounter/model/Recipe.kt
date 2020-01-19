package com.andreyplis.recipecounter.model

import androidx.room.TypeConverters
import com.andreyplis.recipecounter.db.entity.RecipeEntity


interface Recipe
{
    @TypeConverters(RecipeEntity.RecipeTypeConverter::class)
    enum class TYPE
    {
        DESERT,
        CAKE
    }
    val id:Int?
    val name: String
    val description: String
    val type: TYPE
    val count: Int
    val measureId:Int
}