package com.kodeco.flixflow.feature.ratings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kodeco.flixflow.common.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch

data class MovieWithRatingViewState(val movieName: String, val rating: Int)

class RatingsViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val _movies: MutableStateFlow<List<MovieWithRatingViewState>> = MutableStateFlow(emptyList())
    val movies: Flow<List<MovieWithRatingViewState>> = _movies

    init {
        fetchAllMoviesWithRatings()
    }

    private fun fetchAllMoviesWithRatings() {
        viewModelScope.launch {
            movieRepository.fetchMoviesByCategory()
                .map { moviesByCategories -> moviesByCategories.values.flatten() }
                .zip(movieRepository.fetchMovieRatings()) { movies, ratings ->
                    movies.map { movie ->
                        val rating = ratings[movie.id]!!
                        MovieWithRatingViewState(movie.title, rating)
                    }
                        .sortedByDescending { it.rating }
                }
                .collect { _movies.emit(it) }
        }
    }
}