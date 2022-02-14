package com.tbadhit.submission_bajp_1.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.tbadhit.submission_bajp_1.R
import com.tbadhit.submission_bajp_1.data.source.local.entity.MovieEntity
import com.tbadhit.submission_bajp_1.databinding.ActivityDetailBinding
import com.tbadhit.submission_bajp_1.utils.loadImage
import com.tbadhit.submission_bajp_1.vo.Resource
import com.tbadhit.submission_bajp_1.vo.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModel()

    private var movie: Resource<MovieEntity>? = null
    private var tvShow: Resource<MovieEntity>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val type = intent.getIntExtra(EXTRA_TYPE, -1)
        val typeEnum: TypeCatalogue = TypeCatalogue.values()[type]
        val id = intent.getIntExtra(ID_DATA, -1)

        binding.apply {
            progressBarList.visibility = View.VISIBLE
            nestedScroll.visibility = View.GONE
        }

        when (typeEnum) {
            TypeCatalogue.MOVIE -> {
                viewModel.setSelectedMovie(id)
                viewModel.movie.observe(this) { movie ->
                    if (movie != null) {
                        this.movie = movie
                        showData(movie)
                    }
                }
            }
            TypeCatalogue.TV_SHOW -> {
                viewModel.setSelectedTvShow(id)
                viewModel.tvShow.observe(this) { tvShow ->
                    if (tvShow != null) {
                        this.tvShow = tvShow
                        showData(tvShow)
                    }
                }
            }
        }

        binding.fabFavorite.setOnClickListener {
            setDataToFavorite(typeEnum)
        }
    }

    private fun setDataToFavorite(typeCatalogue: TypeCatalogue) {
        when (typeCatalogue) {
            TypeCatalogue.MOVIE -> {
                viewModel.setFavoriteMovie()
                Toast.makeText(
                    this,
                    if (movie?.data?.isFavorite != true ) {
                        R.string.message_add_favorite
                    } else {
                        R.string.message_remove_favorite
                    }, Toast.LENGTH_SHORT).show()
            }
            TypeCatalogue.TV_SHOW -> {
                viewModel.setFavoriteTvShow()
                Toast.makeText(
                    this,
                    if (movie?.data?.isFavorite != true ) {
                        R.string.message_add_favorite
                    } else {
                        R.string.message_remove_favorite
                    }, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showData(movie: Resource<MovieEntity>) {
        when (movie.status) {
            Status.LOADING -> binding.progressBarList.visibility = View.VISIBLE
            Status.SUCCESS -> if (movie.data != null) {
                binding.progressBarList.visibility = View.GONE
                binding.nestedScroll.visibility = View.VISIBLE

                val state = movie.data.isFavorite
                setFavorite(state)
                loadDataMovie(movie.data)
            }
            Status.ERROR -> {
                binding.progressBarList.visibility = View.GONE
                Toast.makeText(this, R.string.message_error, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setFavorite(state: Boolean) {
        if (state) {
            binding.fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this, R.drawable.ic_favorite_red
                )
            )
        } else {
            binding.fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this, R.drawable.ic_favorite_border
                )
            )
        }
    }

    private fun loadDataMovie(movie: MovieEntity) {
        with(binding) {
            tvTitle.text = movie.title
            tvDesc.text = movie.overview
            tvReleaseDate.text = movie.releaseDate
            tvLanguage.text = movie.originalLanguage
            tvRate.text = movie.voteAverage.toString()
            tvRateCount.text = movie.voteCount.toString()
            imgDetail.loadImage(getString(R.string.url_poster, movie.posterPath))
            imgBackdrop.loadImage(getString(R.string.url_poster, movie.backdropPath))
        }
    }

    companion object {
        const val ID_DATA = "id_data"
        const val EXTRA_TYPE = "extra_type"
    }
}