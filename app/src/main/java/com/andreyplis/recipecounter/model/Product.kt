package com.andreyplis.recipecounter.model

interface Product
{
    val id: Int
    val name: String
    val count: Int
    val measure:String
    val price: Float

}