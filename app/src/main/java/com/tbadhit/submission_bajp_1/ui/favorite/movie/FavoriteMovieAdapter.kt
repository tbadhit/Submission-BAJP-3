package com.tbadhit.submission_bajp_1.ui.favorite.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tbadhit.submission_bajp_1.R
import com.tbadhit.submission_bajp_1.data.source.local.entity.MovieEntity
import com.tbadhit.submission_bajp_1.databinding.ItemFavoriteBinding
import com.tbadhit.submission_bajp_1.ui.detail.DetailActivity
import com.tbadhit.submission_bajp_1.ui.detail.TypeCatalogue
import com.tbadhit.submission_bajp_1.utils.loadImage
import com.tbadhit.submission_bajp_1.utils.startActivity

class FavoriteMovieAdapter: PagedListAdapter<MovieEntity, FavoriteMovieAdapter.ViewHolder>(
    DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemsBinding = ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemsBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }

    inner class ViewHolder(private val binding: ItemFavoriteBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding) {
                tvTitle.text = movie.title
                tvReleaseDate.text = movie.releaseDate
                tvLanguage.text = movie.originalLanguage
                tvRate.text = movie.voteAverage.toString()
                tvRateCount.text = movie.voteCount.toString()
                imgMovie.loadImage(itemView.context.getString(R.string.url_poster, movie.posterPath))
            }

            itemView.setOnClickListener {
                itemView.context.startActivity<DetailActivity>(
                    DetailActivity.EXTRA_TYPE to TypeCatalogue.MOVIE.ordinal,
                    DetailActivity.ID_DATA to movie.id
                )
            }
        }
    }

    fun getSwipedData(swipedPosition: Int): MovieEntity? = getItem(swipedPosition)

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean =
                oldItem == newItem

        }
    }
}