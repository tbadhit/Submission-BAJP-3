package com.tbadhit.submission_bajp_1.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.tbadhit.submission_bajp_1.data.ItemEntity
import com.tbadhit.submission_bajp_1.databinding.ActivityDetailBinding
import com.tbadhit.submission_bajp_1.ui.movie.MovieFragment.Companion.MOVIE
import com.tbadhit.submission_bajp_1.ui.movie.MovieViewModel
import com.tbadhit.submission_bajp_1.ui.tvshow.TvShowFragment.Companion.TV_SHOW
import com.tbadhit.submission_bajp_1.ui.tvshow.TvShowViewModel
import com.tbadhit.submission_bajp_1.utils.loadImage

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        when (intent.getIntExtra(CLICK, 0)) {
            MOVIE -> {
                val movieViewModel = ViewModelProvider(
                    this,
                    ViewModelProvider.NewInstanceFactory()
                )[MovieViewModel::class.java]
                val movie = movieViewModel.getMovie(intent.getIntExtra(EXTRA_ITEM, 0))
                populateItem(movie)
            }
            TV_SHOW -> {
                val tvShowViewModel = ViewModelProvider(
                    this,
                    ViewModelProvider.NewInstanceFactory()
                )[TvShowViewModel::class.java]
                val tvShow = tvShowViewModel.getTvShow(intent.getIntExtra(EXTRA_ITEM, 0))
                populateItem(tvShow)
            }
        }
    }

    private fun populateItem(item: ItemEntity) {
        binding.apply {
            tvTitleDetail.text = item.title
            tvDescDetail.text = item.description
            tvGenre.text = item.genre
            tvYear.text = item.releaseYear
            tvDuration.text = item.duration
            tvRate.text = item.rate.toString()
            imgDetail.loadImage(item.imagePath)
            imgBackdrop.loadImage(item.backdropsImagePath)
        }
    }

    companion object {
        const val EXTRA_ITEM = "extra_item"
        const val CLICK = "click"
    }
}