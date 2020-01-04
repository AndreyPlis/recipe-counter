package com.andreyplis.recipecounter.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.andreyplis.recipecounter.model.Product

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    override val id: Int,
    override val name: String,
    override val measure: String,
    override val count: Int,
    override val price: Float
) : Product {

}