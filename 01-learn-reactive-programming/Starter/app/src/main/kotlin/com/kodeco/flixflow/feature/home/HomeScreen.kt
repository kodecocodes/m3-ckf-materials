package com.kodeco.flixflow.feature.home

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kodeco.flixflow.R
import com.kodeco.flixflow.common.Movie
import com.kodeco.flixflow.common.MovieCategory
import com.kodeco.flixflow.common.movieImages
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
  homeView: HomeView,
  onRatingsClicked: () -> Unit,
  onCategoryClicked: (String) -> Unit,
  onMovieClicked: (String) -> Unit
) {
  val viewModel = koinViewModel<HomeViewModel>()

  val categories by homeView.categories.collectAsState(initial = emptyList())
  val moviesByCategories by homeView.moviesByCategories.collectAsState(initial = emptyMap())

  RootContainer(
    categories = categories,
    moviesByCategories = moviesByCategories,
    onRatingsClicked = onRatingsClicked,
    onCategoryClicked = onCategoryClicked,
    onMovieClicked = onMovieClicked
  )
}

@Composable
private fun RootContainer(
  categories: List<MovieCategory>,
  moviesByCategories: Map<String, List<Movie>>,
  onRatingsClicked: () -> Unit,
  onCategoryClicked: (String) -> Unit,
  onMovieClicked: (String) -> Unit
) {
  Box(
    modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)
  ) {
    val scrollState = rememberScrollState()
    Column(
      modifier = Modifier
          .fillMaxSize()
          .verticalScroll(state = scrollState, flingBehavior = ScrollableDefaults.flingBehavior())
    ) {
      TopBar(onRatingsClicked)
      CategoriesSection(categories = categories, onCategoryClicked = onCategoryClicked)
      MovieCategories(moviesByCategories = moviesByCategories, onMovieClicked = onMovieClicked)
    }
  }
}

@Composable
fun TopBar(onRatingsClicked: () -> Unit) {
  Row(
    modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    Text(
      text = "FlixFlow",
      fontSize = 24.sp,
      color = Color.White
    )
    IconButton(onClick = onRatingsClicked) {
      Icon(
        painter = painterResource(id = R.drawable.baseline_movie_filter_24),
        contentDescription = "Placeholder Icon",
        modifier = Modifier.size(48.dp),
        tint = Color.White
      )
    }
  }
}

@Composable
fun CategoriesSection(categories: List<MovieCategory>, onCategoryClicked: (String) -> Unit) {
  LazyRow(
    modifier = Modifier.padding(vertical = 8.dp),
    horizontalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    item {
      Spacer(modifier = Modifier.width(8.dp))
    }
    items(categories.size) { index ->
      CategoryChip(category = categories[index], onChipClicked = onCategoryClicked)
    }
  }
}

@Composable
fun CategoryChip(
  category: MovieCategory,
  onChipClicked: (String) -> Unit
) {
  val interactionSource = remember { MutableInteractionSource() }
  Box(
    modifier = Modifier
        .clip(shape = RoundedCornerShape(50))
        .background(color = Color.DarkGray, shape = RoundedCornerShape(50))
        .clickable(
            interactionSource = interactionSource,
            indication = rememberRipple(),
            onClick = { onChipClicked.invoke(category.id) }
        )
        .padding(horizontal = 16.dp, vertical = 8.dp)
  ) {
    Text(text = category.name, color = Color.White)
  }
}

@Composable
fun MovieCategories(
  moviesByCategories: Map<String, List<Movie>>,
  onMovieClicked: (String) -> Unit
) {
  moviesByCategories.forEach { (category, movies) ->
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
      Row {
        Text(
          text = category, modifier = Modifier
                .padding(start = 16.dp, bottom = 8.dp)
                .align(Alignment.CenterVertically),
          color = Color.White
        )
      }
      MovieCardsRow(movies = movies, onMovieClicked = onMovieClicked)
    }
  }
}

@Composable
fun MovieCardsRow(movies: List<Movie>, onMovieClicked: (String) -> Unit) {
  LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
    item {
      Spacer(modifier = Modifier.width(8.dp))
    }
    items(movies) { movie ->
      MovieCard(movie = movie, onClicked = onMovieClicked)
    }
    item {
      Spacer(modifier = Modifier.width(8.dp))
    }
  }
}

@Composable
fun MovieCard(movie: Movie, onClicked: (String) -> Unit) {
  Card(
    modifier = Modifier
      .size(width = 140.dp, height = 200.dp),
    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    onClick = { onClicked.invoke(movie.id) }
  ) {
    Box(modifier = Modifier.fillMaxSize()) {
      Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(id = movieImages[movie.id]!!),
        contentDescription = null,
        contentScale = ContentScale.Crop
      )
      Box(
        modifier = Modifier
          .background(Color.Black.copy(alpha = 0.2f))
      ) {
        Column(
          modifier = Modifier.fillMaxSize()
        ) {
          Icon(
            modifier = Modifier
                .size(48.dp)
                .padding(top = 12.dp)
                .align(CenterHorizontally),
            painter = painterResource(id = R.drawable.baseline_movie_24),
            contentDescription = null,
            tint = Color.White
          )
          Text(
            text = movie.title,
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(start = 8.dp, top = 24.dp, end = 8.dp),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color.White
          )
        }
      }

    }
  }
}