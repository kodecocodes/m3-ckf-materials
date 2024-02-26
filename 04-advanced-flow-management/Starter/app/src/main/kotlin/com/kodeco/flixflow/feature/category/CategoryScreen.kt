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