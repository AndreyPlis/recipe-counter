package com.andreyplis.recipecounter.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.andreyplis.recipecounter.db.entity.ProductEntity
import com.andreyplis.recipecounter.repository.ProductsRepository

class ProductsViewModel(application:Application) :AndroidViewModel(application)
{
    val productsRepository:ProductsRepository = ProductsRepository(application)


    fun getProducts():LiveData<List<ProductEntity>>
    {
        return productsRepository.getProducts()
    }
}