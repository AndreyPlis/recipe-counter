package com.andreyplis.recipecounter.viewmodel

import android.app.*
import androidx.lifecycle.*
import com.andreyplis.recipecounter.db.*
import com.andreyplis.recipecounter.db.entity.*
import com.andreyplis.recipecounter.repository.*

class ProductsViewModel(application: Application) : AndroidViewModel(application) {
    private val productsRepository: ProductsRepository = ProductsRepository(application)


    fun getMeasures():LiveData<List<MeasureEntity>> = productsRepository.measures

    fun getProductsWithMeasure(): LiveData<List<ProductWithMeasure>> {
        return productsRepository.getProductsWithMeasures()
    }

    fun delete(productEntity: ProductEntity)
    {
        productsRepository.delete(productEntity)
    }

    fun insert(productEntity: ProductEntity) {
        productsRepository.insert(productEntity)
    }

    fun update(productEntity: ProductEntity)
    {
        productsRepository.update(productEntity)
    }

}