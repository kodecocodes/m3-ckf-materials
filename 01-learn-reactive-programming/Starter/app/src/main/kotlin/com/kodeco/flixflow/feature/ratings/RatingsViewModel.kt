package com.kodeco.flixflow.feature.ratings

import androidx.lifecycle.ViewModel
import com.kodeco.flixflow.common.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

data class MovieWithRatingViewState(val movieName: String, val rating: Int)

class RatingsViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val _movies: MutableStateFlow<List<MovieWithRatingViewState>> = MutableStateFlow(emptyList())
    val movies: Flow<List<MovieWithRatingViewState>> = _movies

    init {
        // To be implemented
    }
}