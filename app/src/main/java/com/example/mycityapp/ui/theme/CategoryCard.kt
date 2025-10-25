package com.example.mycityapp.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mycityapp.data.Category
import com.example.mycityapp.data.Recommendation
import com.example.mycityapp.model.Datasource

@Composable
fun CategoryListItem(
    category: Category,
    onCategoryClick: (Category) -> Unit,
    modifier: Modifier = Modifier){
    Card (modifier= modifier
        .clickable{
            onCategoryClick(category)
        }){
        Text(
            text = stringResource(category.categoryName),
            style = MaterialTheme.typography.titleMedium,
            modifier= Modifier.padding(8.dp)
        )
    }
}

@Composable
fun MyCityHomeScreen(onCategoryClick: (Category) -> Unit,
                     modifier: Modifier = Modifier ){
    val categoryList = Datasource.getCategories()

    LazyColumn (
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    )
    { items(categoryList){ category ->
        CategoryListItem(
            category = category,
            onCategoryClick = onCategoryClick,
            modifier = modifier.fillMaxWidth()
        )

    }
    }
}

@Composable
fun RecommendationListItem(
    recommendation: Recommendation,
    onRecommendationClick: (Recommendation) -> Unit,
    modifier: Modifier = Modifier
){
    Card(modifier= modifier
        .clickable{
            onRecommendationClick(recommendation)
        }) {
        Row(modifier= Modifier.padding(8.dp)) {
            Image(
                painter = painterResource(recommendation.recommendationImage),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(100.dp,100.dp)
            )
            Column {
                Text(
                    text = stringResource(recommendation.recommendationName),
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(start= 8.dp)
                )
                Text(
                    text = stringResource(recommendation.recommendationDetails),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(start= 8.dp)
                )
            }
        }
    }

}

@Composable
fun RecommendationListScreen(selectedCategoryId: Int?, onRecommendationClick: (Recommendation) -> Unit){
    val selected = Datasource.getCategories().find { it.categoryId == selectedCategoryId }

    selected?.let { category ->
        LazyColumn(
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        )
        {items(category.recommendedPlaces){ recommendation ->
            RecommendationListItem(
                recommendation = recommendation,
                onRecommendationClick = onRecommendationClick,
                modifier = Modifier.fillMaxWidth()
            )
        }

        }
    }
}

@Composable
fun RecommendationDetailScreen (recommendation: Recommendation) {
    Column {
        Image(
            painter = painterResource(recommendation.recommendationHeaderImage),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(5.dp))
        Text(
            text = stringResource(recommendation.recommendationName),
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(start= 8.dp)
        )
        Text(
            text = stringResource(recommendation.recommendationDetails),
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(start= 8.dp)
        )
    }

}
