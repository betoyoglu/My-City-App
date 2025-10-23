package com.example.mycityapp.data

data class Category(
    val categoryId: Int,
    val categoryName: Int,
    val recommendedPlaces : List<Recommendation>
)

