package com.andreyplis.recipecounter.db.dao

import androidx.room.*
import com.andreyplis.recipecounter.db.entity.*

@Dao
interface RecipesDao
{
    @Insert
    fun insert(recipe: RecipeEntity)

    @Update
    fun update(recipe: RecipeEntity)

    @Delete
    fun delete(recipe: RecipeEntity)
}