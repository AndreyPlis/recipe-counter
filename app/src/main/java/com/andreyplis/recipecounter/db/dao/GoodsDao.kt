package com.andreyplis.recipecounter.db.dao

import androidx.lifecycle.*
import androidx.room.*
import com.andreyplis.recipecounter.db.entity.*

@Dao
interface GoodsDao {

    @Insert
    fun insert(good: GoodEntity)

    @Update
    fun update(good: GoodEntity)

    @Delete
    fun delete(good: GoodEntity)


    @Query("select * from goods order by goods.name")
    fun getProducts(): LiveData<List<GoodEntity>>

}