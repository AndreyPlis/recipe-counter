package com.andreyplis.recipecounter.db.dao

import androidx.lifecycle.*
import androidx.room.*
import com.andreyplis.recipecounter.db.entity.*
import com.andreyplis.recipecounter.model.*

@Dao
interface RecipeGoodsDao {

    @Insert
    fun insert(recipe: RecipeGoodEntity)


    @Delete
    fun delete(recipe: RecipeGoodEntity)


    @Query("SELECT goods.id, goods.name, goods.measure, goods.count, goods.price, recipes_goods.count AS recipeCount FROM recipes_goods INNER JOIN goods ON recipes_goods.goodId = goods.id WHERE recipes_goods.recipeId =:recipe ORDER BY goods.name")
    fun getRecipeWithGoods(recipe: Int): LiveData<List<RecipeGood>>


    /*@Query("SELECT  SUM(goods.price)  FROM recipes_goods INNER JOIN goods ON recipes_goods.goodId = goods.id WHERE recipes_goods.recipeId =:recipe")
    fun countRecipePrice(recipe: Int):Float*/

}