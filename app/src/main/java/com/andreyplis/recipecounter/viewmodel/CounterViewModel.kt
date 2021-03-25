package com.andreyplis.recipecounter.viewmodel

import android.app.*
import androidx.lifecycle.*
import com.andreyplis.recipecounter.model.*
import com.andreyplis.recipecounter.repository.*

class CounterViewModel(application: Application) : AndroidViewModel(application) {
    private val recipeGoodsRepository = RecipeGoodsRepository(application)

    fun getGoods(recipe: Int): LiveData<List<RecipeGood>> {
        return recipeGoodsRepository.getRecipeWithGoods(recipe)
    }
}