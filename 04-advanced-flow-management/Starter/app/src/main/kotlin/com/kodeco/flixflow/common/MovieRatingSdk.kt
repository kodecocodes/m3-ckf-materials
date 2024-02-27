package com.kodeco.flixflow.common

interface MovieRatingListener {
  fun onRatingUpdate(movieName: String, newRating: Float)
}

interface MovieRatingService {
  fun addRatingListener(listener: MovieRatingListener)
  fun removeRatingListener(listener: MovieRatingListener)
}

class MovieRatingServiceSdk() : MovieRatingService {
  override fun addRatingListener(listener: MovieRatingListener) {
    // Placeholder
  }

  override fun removeRatingListener(listener: MovieRatingListener) {
    // Placeholder
  }
}