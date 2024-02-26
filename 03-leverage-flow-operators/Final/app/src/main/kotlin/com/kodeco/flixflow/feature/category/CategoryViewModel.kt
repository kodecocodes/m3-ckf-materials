package com.kodeco.flixflow.feature.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kodeco.flixflow.common.MovieRepository
import com.kodeco.flixflow.feature.home.MovieCategoryViewState
import com.kodeco.flixflow.feature.home.MovieViewState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class CategoryScreenViewState(val category: MovieCategoryViewState, val movies: List<MovieViewState>)

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