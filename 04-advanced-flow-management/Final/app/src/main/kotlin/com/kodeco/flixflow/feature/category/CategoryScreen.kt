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

package com.kodeco.flixflow.feature.category

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kodeco.flixflow.feature.home.MovieCard
import com.kodeco.flixflow.feature.home.MovieViewState
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CategoryScreen(
  categoryId: String,
  onBackClicked: () -> Unit
) {
  val viewModel = koinViewModel<CategoryViewModel>()

  LaunchedEffect(Unit) { viewModel.initCategory(categoryId = categoryId) }

  val screenViewState by viewModel.screenViewState.collectAsState(initial = null)
  RootContainer(
    categoryScreenViewState = screenViewState,
    onBackClicked = onBackClicked
  )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RootContainer(
  categoryScreenViewState: CategoryScreenViewState?,
  onBackClicked: () -> Unit
) {
  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(Color.Black)
  )
  if (categoryScreenViewState != null) {
    Scaffold(
      topBar = {
        TopAppBar(
          title = { Text(text = categoryScreenViewState.category.name) },
          navigationIcon = {
            IconButton(onClick = onBackClicked) {
              Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
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
      MovieGrid(movies = categoryScreenViewState.movies, innerPadding = innerPadding)
    }
  }
}

@Composable
fun MovieGrid(movies: List<MovieViewState>, innerPadding: PaddingValues) {
  LazyVerticalGrid(
    columns = GridCells.Fixed(2),
    contentPadding = PaddingValues(8.dp),
    modifier = Modifier
      .fillMaxSize()
      .background(Color.Black)
      .padding(innerPadding),
    horizontalArrangement = Arrangement.spacedBy(8.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp)
  ) {
    items(movies) { movie ->
      MovieCard(movie = movie, onClicked = {})
    }
  }
}