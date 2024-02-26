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

    private val _moviesByCategories: MutableStateFlow<Map<String, List<Movie>>> = MutableStateFlow(emptyMap())
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