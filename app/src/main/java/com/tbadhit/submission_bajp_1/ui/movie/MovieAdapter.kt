package com.tbadhit.submission_bajp_1.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tbadhit.submission_bajp_1.R
import com.tbadhit.submission_bajp_1.data.source.local.entity.MovieEntity
import com.tbadhit.submission_bajp_1.databinding.ItemsBinding
import com.tbadhit.submission_bajp_1.utils.loadImage

class MovieAdapter : PagedListAdapter<MovieEntity,MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    private var listener: ((MovieEntity) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): MovieViewHolder {
        val itemListBinding = ItemsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MovieViewHolder(itemListBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie, listener)
        }

    }

    inner class MovieViewHolder(private val binding: ItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieEntity, listener: ((MovieEntity) -> Unit)?) {
            with(binding) {
                tvTitle.text = movie.title
                tvReleaseDate.text = movie.releaseDate
                tvLanguage.text = movie.originalLanguage
                tvRate.text = movie.voteAverage.toString()
                tvRateCount.text = movie.voteCount.toString()
                imgMovie.loadImage(itemView.context.getString(R.string.url_poster, movie.posterPath))
            }

            itemView.setOnClickListener {
                listener?.invoke(movie)
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