package com.andreyplis.recipecounter.db.entity

import androidx.room.*

@Entity(primaryKeys = ["recipeId", "goodId"])
data class RecipeGoodEntity(
    val recipeId: Int,
    val goodId: Int
)