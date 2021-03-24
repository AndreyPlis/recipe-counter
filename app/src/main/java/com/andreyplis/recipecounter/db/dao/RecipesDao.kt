package com.andreyplis.recipecounter.db.dao

import androidx.lifecycle.*
import androidx.room.*
import com.andreyplis.recipecounter.db.entity.*

@Dao
interface RecipesDao {
    @Insert
    fun insert(recipe: RecipeEntity)

    @Update
    fun update(recipe: RecipeEntity)

    @Delete
    fun delete(recipe: RecipeEntity)

    @Query("select * from recipes order by recipes.description")
    fun getRecipes(): LiveData<List<RecipeEntity>>

    @Query("select * from recipes WHERE recipes.id =:recipe")
    fun getRecipe(recipe: Int): RecipeEntity
}