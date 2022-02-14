package com.tbadhit.submission_bajp_1.ui.movie

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.tbadhit.submission_bajp_1.R
import com.tbadhit.submission_bajp_1.data.source.local.entity.MovieEntity
import com.tbadhit.submission_bajp_1.databinding.FragmentMovieBinding
import com.tbadhit.submission_bajp_1.ui.detail.DetailActivity
import com.tbadhit.submission_bajp_1.ui.detail.TypeCatalogue
import com.tbadhit.submission_bajp_1.utils.SortUtils
import com.tbadhit.submission_bajp_1.utils.startActivity
import com.tbadhit.submission_bajp_1.vo.Resource
import com.tbadhit.submission_bajp_1.vo.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {

    private var _fragmentMovieBinding: FragmentMovieBinding? = null
    private val binding get() = _fragmentMovieBinding!!
    private lateinit var movieAdapter: MovieAdapter

    private val viewModel: MovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragmentMovieBinding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieAdapter = MovieAdapter()
        setList(SortUtils.NEWEST)

        with(binding.rvMovie) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }

        onClick()
    }

    private fun setList(sort: String) {
        viewModel.getMovies(sort).observe(requireActivity(), moviesObserver)
    }

    private fun onClick() {
        binding.apply {
            fabNewest.setOnClickListener { setList(SortUtils.NEWEST) }
            fabOldest.setOnClickListener { setList(SortUtils.OLDEST) }
            fabPopularity.setOnClickListener { setList(SortUtils.RATE) }
        }

        movieAdapter.onClick { data ->
            requireContext().startActivity<DetailActivity>(
                DetailActivity.EXTRA_TYPE to TypeCatalogue.MOVIE.ordinal,
                DetailActivity.ID_DATA to data.id
            )
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private val moviesObserver = Observer<Resource<PagedList<MovieEntity>>> { movies ->
        if (movies != null) {
            when(movies.status) {
                Status.LOADING -> binding.progressBarList.visibility = View.VISIBLE
                Status.SUCCESS -> {
                    binding.progressBarList.visibility = View.GONE
                    movieAdapter.apply {
                        submitList(movies.data)
                        notifyDataSetChanged()
                    }
                }
                Status.ERROR -> {
                    binding.progressBarList.visibility = View.GONE
                    Toast.makeText(context, R.string.message_error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentMovieBinding = null
    }
}