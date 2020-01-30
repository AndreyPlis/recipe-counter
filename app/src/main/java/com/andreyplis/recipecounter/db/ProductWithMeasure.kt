package com.andreyplis.recipecounter.db

import androidx.room.*
import com.andreyplis.recipecounter.db.entity.*

data class ProductWithMeasure
    (
    @Embedded
    val productEntity:ProductEntity,
    val measure:String
)