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
