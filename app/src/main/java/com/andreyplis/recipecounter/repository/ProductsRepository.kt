package com.andreyplis.recipecounter.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.andreyplis.recipecounter.db.ApplicationDatabase
import com.andreyplis.recipecounter.db.dao.ProductsDao
import com.andreyplis.recipecounter.db.entity.ProductEntity

class ProductsRepository(application: Application) {
    private val productsDao: ProductsDao =
        ApplicationDatabase.getInstance(application).getProductDao()

    fun getProducts(): LiveData<List<ProductEntity>> {
        return productsDao.getProducts()
    }

    fun insert(productEntity: ProductEntity) {
        RepositoryTask(operation = { productsDao.insert(productEntity) }).execute()
    }


    companion object {
        class RepositoryTask(val operation: () -> Unit) : AsyncTask<ProductEntity, Unit, Unit>() {
            override fun doInBackground(vararg params: ProductEntity?) {
                operation()
            }


        }
    }


}