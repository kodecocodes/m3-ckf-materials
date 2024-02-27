package com.kodeco.flixflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kodeco.flixflow.feature.category.CategoryScreen
import com.kodeco.flixflow.feature.home.HomeScreen
import com.kodeco.flixflow.feature.ratings.RatingScreen

class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent()
  }

  private fun setContent() {
    setContent {
      val navController = rememberNavController()
      Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
      ) {
        NavHost(
          navController = navController,
          startDestination = "Home"
        ) {
          composable("Home") {
            HomeScreen(
              onRatingsClicked = {
                navController.navigate("Ratings")
              },
              onCategoryClicked = { category ->
                navController.navigate("Category/$category")
              },
              onMovieClicked = {
                // Placeholder
              }
            )
          }
          composable(
            route = "Category/{categoryId}",
            arguments = listOf(navArgument("categoryId") { type = NavType.StringType })
          ) { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId")!!
            CategoryScreen(
              categoryId = categoryId,
              onBackClicked = { navController.popBackStack() }
            )
          }
          composable("Ratings") {
            RatingScreen(onBackClicked = { navController.popBackStack() })
          }
        }
      }
    }
  }
}