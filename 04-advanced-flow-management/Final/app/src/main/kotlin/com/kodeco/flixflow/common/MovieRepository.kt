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

  fun moviesByCategories(): Flow<Map<String, List<Movie>>> = movieDatabase.getMoviesByCategoryFlow()

  suspend fun updateMoviesByCategories(category: String) {
    val moviesForCategory = moviesByCategoryDummyData[category]
    val shuffledMovies = moviesForCategory!!.shuffled()
    movieDatabase.updateMoviesByCategory(category = category, movies = shuffledMovies)
  }
}