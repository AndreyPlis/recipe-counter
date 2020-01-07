package com.andreyplis.recipecounter.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.andreyplis.recipecounter.db.ApplicationDatabase
import com.andreyplis.recipecounter.db.ProductWithMeasure
import com.andreyplis.recipecounter.db.dao.MeasuresDao
import com.andreyplis.recipecounter.db.dao.ProductsDao
import com.andreyplis.recipecounter.db.entity.MeasureEntity
import com.andreyplis.recipecounter.db.entity.ProductEntity

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