package com.andreyplis.recipecounter.viewmodel

import android.app.*
import androidx.lifecycle.*
import com.andreyplis.recipecounter.db.entity.*
import com.andreyplis.recipecounter.repository.*

class GoodsViewModel(application: Application) : AndroidViewModel(application) {
    private val goodsRepository: GoodsRepository = GoodsRepository(application)


    fun getProducts(): LiveData<List<GoodEntity>> {
        return goodsRepository.getProducts()
    }

    fun delete(goodEntity: GoodEntity) {
        goodsRepository.delete(goodEntity)
    }

    fun insert(goodEntity: GoodEntity) {
        goodsRepository.insert(goodEntity)
    }

    fun update(goodEntity: GoodEntity) {
        goodsRepository.update(goodEntity)
    }

}