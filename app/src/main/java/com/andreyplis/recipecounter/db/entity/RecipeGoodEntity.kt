package com.andreyplis.recipecounter.db.entity

import androidx.room.*

@Entity(tableName = "recipes_goods", primaryKeys = ["recipeId", "goodId"])
data class RecipeGoodEntity(
    val recipeId: Int,
    val goodId: Int,
    val count: Int,
)