package com.kodeco.flixflow.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class MovieDatabase {
  private val moviesByCategory = MutableStateFlow<Map<String, List<Movie>>>(emptyMap())

  init {
    moviesByCategory.value = moviesByCategoryDummyData
  }

  fun getMoviesByCategoryFlow(): Flow<Map<String, List<Movie>>> = moviesByCategory

  suspend fun updateMoviesByCategory(category: String, movies: List<Movie>) {
    val currentData = moviesByCategory.value.toMutableMap()
    currentData[category] = movies
    moviesByCategory.emit(currentData)
  }

  fun getMovieDescription(movieId: String): String {
    return movieDescriptions[movieId]!!
  }
}
