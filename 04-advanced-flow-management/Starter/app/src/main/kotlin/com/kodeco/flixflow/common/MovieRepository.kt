package com.kodeco.flixflow.common

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*

data class MovieCategory(val id: String, val name: String)
data class Movie(val id: String, val title: String)

class MovieRepository(
  private val movieService: MovieService,
  private val movieRatingService: MovieRatingService,
  private val movieDatabase: MovieDatabase
) {

  fun categories(): Flow<MovieCategory> = categoriesDummyData.asFlow()

  fun fetchFavouriteCategories(): Flow<List<MovieCategory>> = flowOf(favouriteCategoriesDummyData)

  fun fetchMoviesByCategory(): Flow<Map<String, List<Movie>>> = flow {
    val moviesByCategory = movieService.fetchMoviesByCategory()
    emit(moviesByCategory)
  }

  fun movieRatings(): Flow<Pair<String, Float>> = callbackFlow {
    val listener = object : MovieRatingListener {
      override fun onRatingUpdate(movieName: String, newRating: Float) {
        trySend(movieName to newRating)
      }
    }

    movieRatingService.addRatingListener(listener)

    awaitClose {
      movieRatingService.removeRatingListener(listener)
    }
  }

  fun fetchMovieDescriptionsSuspending(movieId: String): String {
    return movieDatabase.getMovieDescription(movieId = movieId)
  }

  fun fetchMovieImage(movieId: String): Int {
    return movieImages[movieId] ?: movieImages.values.first()
  }

  fun fetchMoviesForCategory(categoryName: String): Flow<List<Movie>> = flow {
    val movies = movieService.fetchMoviesForCategory(categoryName)
    emit(movies)
  }

  fun fetchMovieRatings(): Flow<Map<String, Int>> = flow {
    val movieRatings = movieService.fetchMovieRatings()
    emit(movieRatings)
  }
}