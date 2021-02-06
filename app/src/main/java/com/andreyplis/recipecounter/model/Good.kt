package com.andreyplis.recipecounter.model

interface Good {
    companion object {
        val MEASURES = mapOf(1 to "кг", 2 to "гр", 3 to "шт", 4 to "л", 5 to "мл")
    }

    val id: Int
    val name: String
    val count: Int
    val measure: Int
    val price: Float
}