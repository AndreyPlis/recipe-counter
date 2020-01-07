package com.andreyplis.recipecounter.db

import androidx.room.Embedded
import com.andreyplis.recipecounter.db.entity.ProductEntity

data class ProductWithMeasure
    (
    @Embedded
    val productEntity:ProductEntity,
    val measure:String
)