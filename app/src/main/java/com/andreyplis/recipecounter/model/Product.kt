package com.andreyplis.recipecounter.model

interface Product
{
    val id: Long?
    val name: String
    val count: Int
    val measureId:Int
    val price: Float

}