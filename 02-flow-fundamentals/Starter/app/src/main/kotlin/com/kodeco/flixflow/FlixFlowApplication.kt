package com.kodeco.flixflow

import android.app.Application
import com.kodeco.flixflow.common.MovieDatabase
import com.kodeco.flixflow.common.MovieRatingServiceSdk
import com.kodeco.flixflow.common.MovieRepository
import com.kodeco.flixflow.common.MovieService
import com.kodeco.flixflow.feature.category.CategoryViewModel
import com.kodeco.flixflow.feature.home.HomeView
import com.kodeco.flixflow.feature.home.HomeViewModel
import com.kodeco.flixflow.feature.ratings.RatingsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class FlixFlowApplication : Application() {

  override fun onCreate() {
    super.onCreate()

    startKoin {
      androidContext(this@FlixFlowApplication)
      modules(
        module {
          single { MovieRepository(MovieService(), MovieRatingServiceSdk(), MovieDatabase()) }
          viewModel { HomeViewModel(get()) }
          viewModel { CategoryViewModel(get()) }
          viewModel { RatingsViewModel(get()) }
          single { HomeView() }
        }
      )
    }
  }
}