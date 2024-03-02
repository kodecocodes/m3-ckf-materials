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

package com.kodeco.flixflow.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kodeco.flixflow.common.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class MovieCategoryViewState(val id: String, val name: String)
data class MovieViewState(
  val id: String,
  val title: String,
  val description: String,
  val imageRes: Int
)

class HomeViewModel(
  private val movieRepository: MovieRepository,
) : ViewModel() {

  private val _categories: MutableStateFlow<List<MovieCategoryViewState>> =
    MutableStateFlow(emptyList())
  val categories: Flow<List<MovieCategoryViewState>> = _categories

  private val _moviesByCategories: MutableStateFlow<Map<String, List<MovieViewState>>> =
    MutableStateFlow(emptyMap())
  val moviesByCategories: Flow<Map<String, List<MovieViewState>>> = _moviesByCategories

  init {
    fetchMoviesByCategories()
    fetchFavouriteCategories()
  }

  private fun fetchMoviesByCategories() {
    viewModelScope.launch {
      movieRepository.fetchMoviesByCategory()
        .transform { moviesByCategories ->
          val movieViewStates: Map<String, List<MovieViewState>> =
            moviesByCategories.mapValues { (category, movies) ->
              movies.map { movie ->
                val description =
                  movieRepository.fetchMovieDescriptionsSuspending(movieId = movie.id)
                val imageRes = movieRepository.fetchMovieImage(movieId = movie.id)
                MovieViewState(
                  id = movie.id,
                  title = movie.title,
                  description = description,
                  imageRes = imageRes
                )
              }
            }
          emit(movieViewStates)
        }
        .collect {
          _moviesByCategories.emit(it)
        }
    }
  }

  private fun fetchFavouriteCategories() {
    viewModelScope.launch {
      movieRepository.fetchFavouriteCategories()
        .map { favouriteCategories ->
          favouriteCategories.map {
            MovieCategoryViewState(
              it.id,
              it.name
            )
          }
        }
        .collect { _categories.emit(it) }
    }
  }
}