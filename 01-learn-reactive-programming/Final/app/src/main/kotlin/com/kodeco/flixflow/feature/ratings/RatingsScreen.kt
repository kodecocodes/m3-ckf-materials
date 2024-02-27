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

package com.kodeco.flixflow.feature.ratings

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.koinViewModel

@Composable
fun RatingScreen(onBackClicked: () -> Unit) {
  val viewModel: RatingsViewModel = koinViewModel()

  val moviesWithRatings by viewModel.movies.collectAsState(initial = emptyList())

  RootContainer(
    moviesWithRatings = moviesWithRatings,
    onBackClicked = onBackClicked
  )
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun RootContainer(
  moviesWithRatings: List<MovieWithRatingViewState>,
  onBackClicked: () -> Unit
) {
  Scaffold(
    topBar = {
      TopAppBar(
        title = { Text(text = "Movie Ratings") },
        navigationIcon = {
          IconButton(onClick = onBackClicked) {
            Icon(
              Icons.AutoMirrored.Filled.ArrowBack,
              contentDescription = "Back",
              tint = Color.White
            )
          }
        },
        colors = TopAppBarColors(
          containerColor = Color.Black,
          scrolledContainerColor = Color.Black,
          navigationIconContentColor = Color.White,
          titleContentColor = Color.White,
          actionIconContentColor = Color.White,
        )
      )
    }
  ) { innerPadding ->
    Box(
      modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)
    ) {
      val scrollState = rememberScrollState()
      Column(
        modifier = Modifier
          .fillMaxSize()
          .padding(16.dp)
          .verticalScroll(state = scrollState)
      ) {
        Text(
          text = "Movie Ratings",
          fontSize = 24.sp,
          modifier = Modifier.padding(bottom = 16.dp),
          color = Color.White
        )
        moviesWithRatings.forEach { movieWithRating ->
          MovieRatingRow(movieWithRating = movieWithRating)
        }
      }
    }
  }
}

@Composable
fun MovieRatingRow(movieWithRating: MovieWithRatingViewState) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(vertical = 8.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    Text(
      text = movieWithRating.movieName,
      fontSize = 18.sp,
      fontWeight = FontWeight.Medium,
      color = Color.White
    )
    RatingBar(rating = movieWithRating.rating)
  }
}

@Composable
fun RatingBar(rating: Int) {
  Row {
    for (i in 1..5) {
      Icon(
        imageVector = if (i <= rating) Icons.Filled.Star else Icons.Outlined.Star,
        contentDescription = null,
        tint = if (i <= rating) Color.Yellow else Color.Gray,
        modifier = Modifier.size(24.dp)
      )
    }
  }
}
