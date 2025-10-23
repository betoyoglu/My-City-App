package com.example.mycityapp.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mycityapp.data.Category
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
fun RecommendationListScreen(selectedCategoryId: Int?){
    val selected = Datasource.getCategories().find { it.categoryId == selectedCategoryId }


}
