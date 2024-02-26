package com.kodeco.flixflow.common

data class MovieCategory(val id: String, val name: String)
data class Movie(val id: String, val title: String)

class MovieRepository(
    private val movieService: MovieService,
    private val movieRatingService: MovieRatingService,
    private val movieDatabase: MovieDatabase
) {

    suspend fun favouriteCategories(): List<MovieCategory> {
        return favouriteCategoriesDummyData
    }

    suspend fun fetchMoviesByCategorySuspending(): Map<String, List<Movie>> {
        return movieService.fetchMoviesByCategory()
    }
}