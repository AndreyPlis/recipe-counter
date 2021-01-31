package com.andreyplis.recipecounter.repository

import android.app.*
import android.os.*
import androidx.lifecycle.*
import com.andreyplis.recipecounter.db.*
import com.andreyplis.recipecounter.db.dao.*
import com.andreyplis.recipecounter.db.entity.*

class RecipesRepository(application: Application) {
    private val recipesDao: RecipesDao =
        ApplicationDatabase.getInstance(application).getRecipesDao()


    fun getRecipes(): LiveData<List<RecipeEntity>> {
        return recipesDao.getRecipes()
    }

    fun delete(recipe: RecipeEntity) {
        RepositoryTask(operation = { recipesDao.delete(recipe) }).execute()
    }


    fun insert(recipe: RecipeEntity) {
        RepositoryTask(operation = { recipesDao.insert(recipe) }).execute()
    }

    fun update(recipe: RecipeEntity) {
        RepositoryTask(operation = { recipesDao.update(recipe) }).execute()
    }


    companion object {
        class RepositoryTask(val operation: () -> Unit) : AsyncTask<ProductEntity, Unit, Unit>() {
            override fun doInBackground(vararg params: ProductEntity?) {
                operation()
            }


        }
    }
}