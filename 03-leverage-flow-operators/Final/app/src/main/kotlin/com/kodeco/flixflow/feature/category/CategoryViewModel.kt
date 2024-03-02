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

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kodeco.flixflow.common.MovieRepository
import com.kodeco.flixflow.feature.home.MovieCategoryViewState
import com.kodeco.flixflow.feature.home.MovieViewState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class CategoryScreenViewState(
  val category: MovieCategoryViewState,
  val movies: List<MovieViewState>
)

class CategoryViewModel(private val movieRepository: MovieRepository) : ViewModel() {

  private val _screenViewState: MutableStateFlow<CategoryScreenViewState?> = MutableStateFlow(null)
  val screenViewState: Flow<CategoryScreenViewState?> = _screenViewState

  fun initCategory(categoryId: String) {
    viewModelScope.launch {
      combine(
        category(categoryId),
        moviesForCategory(categoryId),
      ) { category, movies -> CategoryScreenViewState(category, movies) }
        .collect { _screenViewState.emit(it) }
    }
  }

  private fun category(categoryId: String): Flow<MovieCategoryViewState> =
    movieRepository.categories()
      .filter { category -> category.id == categoryId }
      .map { category -> MovieCategoryViewState(category.id, category.name) }

  @OptIn(ExperimentalCoroutinesApi::class)
  private fun moviesForCategory(categoryId: String): Flow<List<MovieViewState>> =
    movieRepository.categories()
      .filter { it.id == categoryId }
      .flatMapLatest { movieCategory -> movieRepository.fetchMoviesForCategory(movieCategory.name) }
      .map { movies ->
        movies.map { movie ->
          val imageRes = movieRepository.fetchMovieImage(movieId = movie.id)
          MovieViewState(movie.id, movie.title, "", imageRes)
        }
      }
}