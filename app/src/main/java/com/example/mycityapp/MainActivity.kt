package com.example.mycityapp

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mycityapp.data.Category
import com.example.mycityapp.data.Recommendation
import com.example.mycityapp.model.Datasource
import com.example.mycityapp.navigation.Routes
import com.example.mycityapp.navigation.Routes.recommendation
import com.example.mycityapp.ui.theme.MyCityAppTheme
import com.example.mycityapp.ui.theme.MyCityHomeScreen
import com.example.mycityapp.ui.theme.RecommendationDetailScreen
import com.example.mycityapp.ui.theme.RecommendationListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyCityAppTheme {
                val navController = rememberNavController()
                val backStackEntry by navController.currentBackStackEntryAsState()

                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        val route = backStackEntry?.destination?.route

                        val titleResId = when {
                            route?.startsWith("details/") == true -> com.example.mycityapp.R.string.details_screen_title
                            route?.startsWith("recommendation/") == true -> com.example.mycityapp.R.string.recommendations_screen_title
                            else -> com.example.mycityapp.R.string.app_name
                        }

                        AppBar(
                            currentScreenTitle = titleResId,
                            canNavigateBack = navController.previousBackStackEntry != null,
                            navigateUp = { navController.navigateUp() }
                        )
                    } )
                { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Routes.home,
                        modifier = Modifier.padding(innerPadding)
                    ){
                        composable(Routes.home) {
                            MyCityHomeScreen(
                                onCategoryClick = { category ->
                                    navController.navigate("recommendation/${category.categoryId}")
                                }
                            )
                        }

                        composable(route = "recommendation/{categoryId}",
                            arguments = listOf(navArgument("categoryId") {type= NavType.IntType})
                        ){backStackEntry ->
                            val selectedId = backStackEntry.arguments?.getInt("categoryId")
                            RecommendationListScreen(selectedCategoryId = selectedId, onRecommendationClick = {recommendation ->
                                navController.navigate("details/${recommendation.recommendationName}")
                            })
                        }

                        composable(
                            route= "details/{recommendationId}",
                            arguments = listOf(navArgument("recommendationId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val recommendationId = backStackEntry.arguments?.getInt("recommendationId")

                            //Tüm kategorilerdeki tüm mekanları tek bir listeye düzleştir (flatMap).
                            val allRecommendations = Datasource.getCategories().flatMap { category ->
                                category.recommendedPlaces
                            }

                            val selectedRecommendation = allRecommendations.find {
                                it.recommendationName == recommendationId
                            }

                            if (selectedRecommendation != null) {
                                RecommendationDetailScreen(recommendation = selectedRecommendation)
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(@StringRes currentScreenTitle: Int, canNavigateBack: Boolean,
           navigateUp: () -> Unit, modifier: Modifier = Modifier){
    CenterAlignedTopAppBar(
        title = {Text(stringResource(currentScreenTitle))},
        modifier= modifier,
        navigationIcon = {
            if(canNavigateBack){
                IconButton(onClick = navigateUp) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = null)
                }
            }
        }
    )
}