package com.example.mycityapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mycityapp.data.Category
import com.example.mycityapp.navigation.Routes
import com.example.mycityapp.ui.theme.MyCityAppTheme
import com.example.mycityapp.ui.theme.MyCityHomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyCityAppTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
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
                            Text(text = "Seçilen Kategori ID: $selectedId")
                        }
                    }
                }
            }
        }
    }
}