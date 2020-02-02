package com.andreyplis.recipecounter.db.entity

import androidx.room.*

@Entity(primaryKeys = ["recipeId", "productId"])
data class RecipeProductEntity(
    val recipeId: Int,
    val productId: Int
)