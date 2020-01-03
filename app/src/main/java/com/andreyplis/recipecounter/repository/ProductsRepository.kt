package com.andreyplis.recipecounter.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.andreyplis.recipecounter.db.ApplicationDatabase
import com.andreyplis.recipecounter.db.dao.ProductsDao
import com.andreyplis.recipecounter.db.entity.ProductEntity

class ProductsRepository {
    val productsDao: ProductsDao

    constructor(application: Application) {
        productsDao = ApplicationDatabase.getInstance(application).getProductDao()
    }

    fun getProducts():LiveData<List<ProductEntity>>
    {
        return productsDao.getProducts()
    }
}