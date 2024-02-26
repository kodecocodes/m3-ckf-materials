package com.kodeco.flixflow.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kodeco.flixflow.common.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val movieRepository: MovieRepository,
    private val homeView: HomeView
) : ViewModel() {

    init {
        renderView()
    }

    private fun renderView() {
        viewModelScope.launch {
            homeView.showLoading()

            val favouriteCategories = movieRepository.favouriteCategories()

            // Filter/Map categories if needed

            val moviesByCategories = movieRepository.fetchMoviesByCategorySuspending()

            // Filter/Map movies if needed

            homeView.hideLoading()
            homeView.renderData(favouriteCategories, moviesByCategories)
        }
    }
}