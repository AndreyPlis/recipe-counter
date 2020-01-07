package com.andreyplis.recipecounter.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.andreyplis.recipecounter.db.ProductWithMeasure
import com.andreyplis.recipecounter.db.entity.MeasureEntity
import com.andreyplis.recipecounter.db.entity.ProductEntity
import com.andreyplis.recipecounter.repository.ProductsRepository

class ProductsViewModel(application: Application) : AndroidViewModel(application) {
    val productsRepository: ProductsRepository = ProductsRepository(application)


    fun getProducts(): LiveData<List<ProductEntity>> {
        return productsRepository.getProducts()
    }


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