package com.andreyplis.recipecounter.viewmodel

import android.app.*
import androidx.lifecycle.*
import com.andreyplis.recipecounter.db.entity.*
import com.andreyplis.recipecounter.repository.*

class RecipesViewModel(application: Application) : AndroidViewModel(application) {
    private val recipeRepository: RecipesRepository = RecipesRepository(application)
    fun getRecipes(): LiveData<List<RecipeEntity>> {
        return recipeRepository.getRecipes()
    }

    fun delete(recipe: RecipeEntity) {
        recipeRepository.delete(recipe)
    }

}