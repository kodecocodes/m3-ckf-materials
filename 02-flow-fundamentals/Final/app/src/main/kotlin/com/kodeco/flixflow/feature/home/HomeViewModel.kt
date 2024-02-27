package com.kodeco.flixflow.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kodeco.flixflow.common.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
  private val movieRepository: MovieRepository,
) : ViewModel() {

  private val _categories: MutableStateFlow<List<MovieCategory>> = MutableStateFlow(emptyList())
  val categories: Flow<List<MovieCategory>> = _categories

  private val _moviesByCategories: MutableStateFlow<Map<String, List<Movie>>> =
    MutableStateFlow(emptyMap())
  val moviesByCategories: Flow<Map<String, List<Movie>>> = _moviesByCategories

  init {
    fetchMoviesByCategories()
    fetchFavouriteCategories()
  }

  private fun fetchMoviesByCategories() {
    viewModelScope.launch {
      movieRepository.fetchMoviesByCategory()
        .collect {
          _moviesByCategories.emit(it)
        }
    }
  }

  private fun fetchFavouriteCategories() {
    viewModelScope.launch {
      movieRepository.fetchFavouriteCategories()
        .collect { _categories.emit(it) }
    }
  }
}