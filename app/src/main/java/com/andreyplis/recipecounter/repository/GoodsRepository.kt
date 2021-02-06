package com.andreyplis.recipecounter.repository

import android.app.*
import android.os.*
import androidx.lifecycle.*
import com.andreyplis.recipecounter.db.*
import com.andreyplis.recipecounter.db.dao.*
import com.andreyplis.recipecounter.db.entity.*

class GoodsRepository(application: Application) {
    private val goodsDao: GoodsDao =
        ApplicationDatabase.getInstance(application).getProductDao()


    fun getProducts(): LiveData<List<GoodEntity>> {
        return goodsDao.getProducts()
    }

    fun insert(goodEntity: GoodEntity) {
        RepositoryTask(operation = { goodsDao.insert(goodEntity) }).execute()
    }

    fun delete(goodEntity: GoodEntity) {
        RepositoryTask(operation = {
            goodsDao.delete(goodEntity)
        }).execute()
    }

    fun update(goodEntity: GoodEntity) {
        RepositoryTask(operation = {
            goodsDao.update(goodEntity)
        }).execute()
    }


    companion object {
        class RepositoryTask(val operation: () -> Unit) : AsyncTask<GoodEntity, Unit, Unit>() {
            override fun doInBackground(vararg params: GoodEntity?) {
                operation()
            }


        }
    }


}