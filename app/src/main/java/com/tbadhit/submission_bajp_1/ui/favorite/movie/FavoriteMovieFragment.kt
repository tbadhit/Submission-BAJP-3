package com.tbadhit.submission_bajp_1.ui.favorite.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.tbadhit.submission_bajp_1.R
import com.tbadhit.submission_bajp_1.databinding.FragmentFavoriteMovieBinding
import com.tbadhit.submission_bajp_1.ui.favorite.FavoriteViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteMovieFragment : Fragment() {

    private var _fragmentFavoriteMovieBinding: FragmentFavoriteMovieBinding? = null
    private val binding get() = _fragmentFavoriteMovieBinding

    private val viewModel: FavoriteViewModel by viewModel()
    private lateinit var favoriteMovieAdapter: FavoriteMovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentFavoriteMovieBinding =
            FragmentFavoriteMovieBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemTouchHelper.attachToRecyclerView(binding?.rvFavoriteMovie)

        favoriteMovieAdapter = FavoriteMovieAdapter()

        binding?.progressBarList?.visibility = View.VISIBLE
        viewModel.getFavoriteMovies().observe(requireActivity()) { movies ->
            binding?.progressBarList?.visibility = View.GONE
            favoriteMovieAdapter.submitList(movies)
        }

        binding?.rvFavoriteMovie?.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = favoriteMovieAdapter
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int {
            return makeMovementFlags(
                0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            )
        }

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.bindingAdapterPosition
                val movieEntity = favoriteMovieAdapter.getSwipedData(swipedPosition)
                movieEntity?.let {
                    viewModel.setFavoriteDataMovie(it)
                }
                val snackBar =
                    Snackbar.make(view as View, R.string.undo_delete, Snackbar.LENGTH_LONG)
                snackBar.setAction(R.string.ok) { _ ->
                    movieEntity?.let {
                        viewModel.setFavoriteDataMovie(it)
                    }
                }
                snackBar.show()
            }
        }
    })

    override fun onDestroy() {
        super.onDestroy()
        _fragmentFavoriteMovieBinding = null
    }
}