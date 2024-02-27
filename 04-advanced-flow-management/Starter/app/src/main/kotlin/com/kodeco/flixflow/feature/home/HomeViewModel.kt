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

  fun refreshCategory(category: String) {
    // To be implemented
  }
}