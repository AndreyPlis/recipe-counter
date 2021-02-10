package com.andreyplis.recipecounter.model

data class RecipeGood(
    val id: Int = 0,
    val name: String,
    val measure: Int,
    val count: Int,
    val price: Float,
    val recipeCount: Int
)
