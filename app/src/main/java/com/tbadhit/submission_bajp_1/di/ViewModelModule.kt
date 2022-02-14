package com.tbadhit.submission_bajp_1.di
import com.tbadhit.submission_bajp_1.ui.detail.DetailViewModel
import com.tbadhit.submission_bajp_1.ui.favorite.FavoriteViewModel
import com.tbadhit.submission_bajp_1.ui.movie.MovieViewModel
import com.tbadhit.submission_bajp_1.ui.tvshow.TvShowViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
}