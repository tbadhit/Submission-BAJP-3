package com.tbadhit.submission_bajp_1.ui.favorite.tvshow

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

class FavoriteTvShowAdapter: PagedListAdapter<MovieEntity, FavoriteTvShowAdapter.ViewHolder>(
    DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) {
            holder.bind(tvShow)
        }
    }

    inner class ViewHolder(private val binding: ItemFavoriteBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: MovieEntity) {
            with(binding) {
                tvTitle.text = tvShow.title
                tvReleaseDate.text = tvShow.releaseDate
                tvLanguage.text = tvShow.originalLanguage
                tvRate.text = tvShow.voteAverage.toString()
                tvRateCount.text = tvShow.voteCount.toString()
                imgMovie.loadImage(itemView.context.getString(R.string.url_poster, tvShow.posterPath))
            }

            itemView.setOnClickListener {
                itemView.context.startActivity<DetailActivity>(
                    DetailActivity.EXTRA_TYPE to TypeCatalogue.MOVIE.ordinal,
                    DetailActivity.ID_DATA to tvShow.id
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