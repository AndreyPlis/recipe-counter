package com.andreyplis.recipecounter.repository

import android.app.*
import android.os.*
import androidx.lifecycle.*
import com.andreyplis.recipecounter.db.*
import com.andreyplis.recipecounter.db.dao.*
import com.andreyplis.recipecounter.db.entity.*
import com.andreyplis.recipecounter.model.*

class RecipeGoodsRepository(application: Application) {
    private val recipeGoodsDao: RecipeGoodsDao =
        ApplicationDatabase.getInstance(application).getRecipeGoodsDao()


    fun getRecipeWithGoods(recipe: Int): LiveData<List<RecipeGood>> {
        return recipeGoodsDao.getRecipeWithGoods(recipe)
    }

    fun insert(recipeGoodEntity: RecipeGoodEntity) {
        RepositoryTask(operation = { recipeGoodsDao.insert(recipeGoodEntity) }).execute()
    }

    fun delete(recipeGoodEntity: RecipeGoodEntity) {
        RepositoryTask(operation = {
            recipeGoodsDao.delete(recipeGoodEntity)
        }).execute()
    }

    companion object {
        class RepositoryTask(val operation: () -> Unit) : AsyncTask<GoodEntity, Unit, Unit>() {
            override fun doInBackground(vararg params: GoodEntity?) {
                operation()
            }
        }
    }
}