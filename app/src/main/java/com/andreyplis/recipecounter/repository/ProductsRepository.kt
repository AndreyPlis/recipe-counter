package com.andreyplis.recipecounter.repository

import android.app.*
import android.os.*
import androidx.lifecycle.*
import com.andreyplis.recipecounter.db.*
import com.andreyplis.recipecounter.db.dao.*
import com.andreyplis.recipecounter.db.entity.*

class ProductsRepository(application: Application) {
    private val productsDao: ProductsDao =
        ApplicationDatabase.getInstance(application).getProductDao()

    private val measuresDao: MeasuresDao =
        ApplicationDatabase.getInstance(application).getMeasureDao()


    val measures: LiveData<List<MeasureEntity>> by lazy {
        measuresDao.getMeasures()
    }


    fun getProducts(): LiveData<List<ProductEntity>> {
        return productsDao.getProducts()
    }

    fun getProductsWithMeasures(): LiveData<List<ProductWithMeasure>> {
        return productsDao.getProductsWithMeasure()
    }

    fun insert(productEntity: ProductEntity) {
        RepositoryTask(operation = { productsDao.insert(productEntity) }).execute()
    }

    fun delete(productEntity: ProductEntity) {
        RepositoryTask(operation = {
            productsDao.delete(productEntity)
        }).execute()
    }

    fun update(productEntity: ProductEntity) {
        RepositoryTask(operation = {
            productsDao.update(productEntity)
        }).execute()
    }


    companion object {
        class RepositoryTask(val operation: () -> Unit) : AsyncTask<ProductEntity, Unit, Unit>() {
            override fun doInBackground(vararg params: ProductEntity?) {
                operation()
            }


        }
    }


}