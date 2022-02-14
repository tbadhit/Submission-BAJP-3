package com.tbadhit.submission_bajp_1.ui.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tbadhit.submission_bajp_1.R
import com.tbadhit.submission_bajp_1.data.source.local.entity.MovieEntity
import com.tbadhit.submission_bajp_1.databinding.ItemsBinding
import com.tbadhit.submission_bajp_1.utils.loadImage

class TvShowAdapter: PagedListAdapter<MovieEntity, TvShowAdapter.TvShowViewHolder>(DIFF_CALLBACK) {

    private var listener: ((MovieEntity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val binding = ItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = getItem(position)
         if (tvShow != null) {
             holder.bind(tvShow, listener)
         }
    }

    inner class TvShowViewHolder(private val binding: ItemsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: MovieEntity, listener: ((MovieEntity) -> Unit)?) {
            with(binding) {
                tvTitle.text = tvShow.title
                tvReleaseDate.text = tvShow.releaseDate
                tvLanguage.text = tvShow.originalLanguage
                tvRate.text = tvShow.voteAverage.toString()
                tvRateCount.text = tvShow.voteCount.toString()
                imgMovie.loadImage(itemView.context.getString(R.string.url_poster, tvShow.posterPath))
            }

            itemView.setOnClickListener {
                listener?.invoke(tvShow)
            }
        }

    }

    fun onClick(listener: ((MovieEntity) -> Unit)?) {
        this.listener = listener
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}