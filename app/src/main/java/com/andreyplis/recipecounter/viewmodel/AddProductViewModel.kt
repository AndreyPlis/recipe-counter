package com.andreyplis.recipecounter.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.andreyplis.recipecounter.db.entity.ProductEntity
import com.andreyplis.recipecounter.repository.ProductsRepository

class AddProductViewModel (application: Application) : AndroidViewModel(application) {
    val productsRepository: ProductsRepository = ProductsRepository(application)

    fun insert(productEntity: ProductEntity) {
        productsRepository.insert(productEntity)
    }
}

