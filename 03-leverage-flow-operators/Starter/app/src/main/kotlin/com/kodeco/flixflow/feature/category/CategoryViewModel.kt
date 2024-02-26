package com.kodeco.flixflow.feature.category

import androidx.lifecycle.ViewModel
import com.kodeco.flixflow.common.MovieRepository
import com.kodeco.flixflow.feature.home.MovieCategoryViewState
import com.kodeco.flixflow.feature.home.MovieViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

data class CategoryScreenViewState(val category: MovieCategoryViewState, val movies: List<MovieViewState>)

class CategoryViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val _screenViewState: MutableStateFlow<CategoryScreenViewState?> = MutableStateFlow(null)
    val screenViewState: Flow<CategoryScreenViewState?> = _screenViewState

    fun initCategory(categoryId: String) {
        // To be implemented
    }
}