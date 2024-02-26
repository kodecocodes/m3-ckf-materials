package com.kodeco.flixflow.common

import kotlinx.coroutines.delay

class MovieService {

    suspend fun fetchMoviesForCategory(categoryName: String): List<Movie> {
        // Simulated network delay
        delay(1000)
        return moviesByCategoryDummyData[categoryName]!!
    }

    suspend fun fetchMoviesByCategory(): Map<String, List<Movie>> {
        // Simulated network delay
        delay(1000)
        return moviesByCategoryDummyData
    }

    suspend fun fetchMovieRatings(): Map<String, Int> {
        // Simulated network delay
        delay(1000)
        return movieRatingsDummyData
    }
}