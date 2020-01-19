package com.andreyplis.recipecounter.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.andreyplis.recipecounter.db.entity.RecipeEntity

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