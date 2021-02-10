package com.andreyplis.recipecounter.viewmodel

import android.app.*
import androidx.lifecycle.*
import com.andreyplis.recipecounter.db.entity.*
import com.andreyplis.recipecounter.model.*
import com.andreyplis.recipecounter.repository.*

class RecipeGoodsViewModel(application: Application) : AndroidViewModel(application) {
    private val recipeGoodsRepository = RecipeGoodsRepository(application)

    fun getRecipeWithGoods(recipe: Int): LiveData<List<RecipeGood>> {
        return recipeGoodsRepository.getRecipeWithGoods(recipe)
    }

    fun delete(recipe: RecipeGoodEntity) {
        recipeGoodsRepository.delete(recipe)
    }

    fun insert(recipe: RecipeGoodEntity) {
        recipeGoodsRepository.insert(recipe)
    }
}