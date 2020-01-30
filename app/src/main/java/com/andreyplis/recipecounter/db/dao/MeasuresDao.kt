package com.andreyplis.recipecounter.db.dao

import androidx.lifecycle.*
import androidx.room.*
import com.andreyplis.recipecounter.db.entity.*


@Dao
interface MeasuresDao {
    @Insert
    fun insert(measureEntity: MeasureEntity)

    @Update
    fun update(measureEntity: MeasureEntity)

    @Delete
    fun delete(measureEntity: MeasureEntity)

    @Query("select * from measures order by measure")
    fun getMeasures(): LiveData<List<MeasureEntity>>
}