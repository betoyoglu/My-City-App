package com.example.mycityapp.model

import com.example.mycityapp.R
import com.example.mycityapp.data.Category
import com.example.mycityapp.data.Recommendation

object Datasource {
    fun getCategories() : List<Category>{
        return listOf(
            Category(
                categoryId = 1,
                categoryName = R.string.category1,
                recommendedPlaces = listOf(
                    Recommendation(
                        recommendationName = R.string.recommendation_coffee1,
                        recommendationDetails = R.string.recommendation_coffee_details,
                        recommendationImage = R.drawable.image
                    ),
                    Recommendation(
                        recommendationName = R.string.recommendation_coffee2,
                        recommendationDetails = R.string.recommendation_coffee_details,
                        recommendationImage = R.drawable.image
                    ),
                    Recommendation(
                        recommendationName = R.string.recommendation_coffee2,
                        recommendationDetails = R.string.recommendation_coffee_details,
                        recommendationImage = R.drawable.image
                    )
                )
            ),
            Category(
                categoryId = 2,
                categoryName = R.string.category2,
                recommendedPlaces = listOf(
                    Recommendation(
                        recommendationName = R.string.recommendation_restaurant1,
                        recommendationDetails = R.string.recommendation_restaurant_details,
                        recommendationImage = R.drawable.image
                    ),
                    Recommendation(
                        recommendationName = R.string.recommendation_restaurant2,
                        recommendationDetails = R.string.recommendation_restaurant_details,
                        recommendationImage = R.drawable.image
                    ),
                    Recommendation(
                        recommendationName = R.string.recommendation_restaurant3,
                        recommendationDetails = R.string.recommendation_restaurant_details,
                        recommendationImage = R.drawable.image
                    )
                )
            ),
            Category(
                categoryId = 3,
                categoryName = R.string.category3,
                recommendedPlaces = listOf(
                    Recommendation(
                        recommendationName = R.string.recommendation_shop1,
                        recommendationDetails = R.string.recommendation_shop_details,
                        recommendationImage = R.drawable.image
                    ),
                    Recommendation(
                        recommendationName = R.string.recommendation_shop2,
                        recommendationDetails = R.string.recommendation_shop_details,
                        recommendationImage = R.drawable.image
                    ),
                    Recommendation(
                        recommendationName = R.string.recommendation_shop3,
                        recommendationDetails = R.string.recommendation_shop_details,
                        recommendationImage = R.drawable.image
                    )
                )
            )
        )

    }
}