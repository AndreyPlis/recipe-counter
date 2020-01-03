package com.andreyplis.recipecounter.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.andreyplis.recipecounter.db.entity.ProductEntity

@Dao
interface ProductsDao {

    @Insert
     fun insert(product: ProductEntity)

    @Update
     fun update(product: ProductEntity)

    @Delete
     fun delete(product: ProductEntity)

    @Query("select * from products order by name")
     fun getProducts():LiveData<List<ProductEntity>>
}