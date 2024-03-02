/*
 * Copyright (c) 2024 Kodeco Inc
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

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