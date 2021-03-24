package com.andreyplis.recipecounter.repository

import android.app.*
import android.os.*
import androidx.lifecycle.*
import com.andreyplis.recipecounter.db.*
import com.andreyplis.recipecounter.db.entity.*
import com.andreyplis.recipecounter.model.*

class RecipeGoodsRepository(application: Application) {
    private val recipeGoodsDao = ApplicationDatabase.getInstance(application).getRecipeGoodsDao()

    private val recipesDao = ApplicationDatabase.getInstance(application).getRecipesDao()


    fun getRecipeWithGoods(recipe: Int): LiveData<List<RecipeGood>> {
        return recipeGoodsDao.getRecipeWithGoods(recipe)
    }

    fun insert(recipeGoodEntity: RecipeGoodEntity) {
        RepositoryTask(operation = {
            recipeGoodsDao.insert(recipeGoodEntity)
        }).execute()
    }

    fun delete(recipeGoodEntity: RecipeGoodEntity) {
        RepositoryTask(operation = {
            recipeGoodsDao.delete(recipeGoodEntity)
        }).execute()
    }


    /* fun recalculate(recipeId: Int) {
         val recipe = recipesDao.getRecipe(recipeId)
         val newPrice = recipeGoodsDao.countRecipePrice(recipeId)
         recipesDao.update(recipe.copy(price = newPrice))
     }*/


    companion object {
        class RepositoryTask(val operation: () -> Unit) : AsyncTask<GoodEntity, Unit, Unit>() {
            override fun doInBackground(vararg params: GoodEntity?) {
                operation()
            }
        }
    }
}