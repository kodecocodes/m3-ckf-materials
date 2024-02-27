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

import com.kodeco.flixflow.common.Movie
import com.kodeco.flixflow.common.MovieCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * This class is only for learning purposes and is used as an imaginary View to explain
 * imperative programming in the context of imperative vs reactive programming.
 */
class HomeView {

  private val _categories: MutableStateFlow<List<MovieCategory>> = MutableStateFlow(emptyList())
  val categories: Flow<List<MovieCategory>> = _categories

  private val _moviesByCategories: MutableStateFlow<Map<String, List<Movie>>> =
    MutableStateFlow(emptyMap())
  val moviesByCategories: Flow<Map<String, List<Movie>>> = _moviesByCategories

  fun showLoading() {
    // Dummy method
  }

  fun hideLoading() {
    // Dummy method
  }

  fun renderData(categories: List<MovieCategory>, movies: Map<String, List<Movie>>) {
    _categories.tryEmit(categories)
    _moviesByCategories.tryEmit(movies)
  }
}