package com.andreyplis.recipecounter.db.dao

import androidx.lifecycle.*
import androidx.room.*
import com.andreyplis.recipecounter.db.*
import com.andreyplis.recipecounter.db.entity.*

@Dao
interface ProductsDao {

    @Insert
    fun insert(product: ProductEntity)

    @Update
    fun update(product: ProductEntity)

    @Delete
    fun delete(product: ProductEntity)


    @Query("select * from products order by products.name")
    fun getProducts(): LiveData<List<ProductEntity>>

    @Query("select products.*, measures.measure from products  inner join measures on products.measureId==measures.id order by products.name")
    fun getProductsWithMeasure(): LiveData<List<ProductWithMeasure>>


}