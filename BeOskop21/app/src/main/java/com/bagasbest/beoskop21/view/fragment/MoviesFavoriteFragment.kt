package com.bagasbest.beoskop21.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bagasbest.beoskop21.R
import com.bagasbest.beoskop21.databinding.FragmentMoviesFavoriteBinding
import com.bagasbest.beoskop21.viewmodel.adapter.FavoriteMovieAdapter
import com.bagasbest.beoskop21.viewmodel.viewmodel.FavoriteViewModel
import com.bagasbest.beoskop21.viewmodel.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar


class MoviesFavoriteFragment : Fragment() {

    private lateinit var binding: FragmentMoviesFavoriteBinding
    private lateinit var viewModel: FavoriteViewModel
    private lateinit var favoriteMovieAdapter: FavoriteMovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMoviesFavoriteBinding.inflate(layoutInflater, container, false)
        setInitViewModel()
        return binding.root
    }

    private fun setInitViewModel() {
        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemTouchHelper.attachToRecyclerView(binding.rvMovies)

        favoriteMovieAdapter = FavoriteMovieAdapter()
        context?.let {
            viewModel.getFavoriteMovies().observe(viewLifecycleOwner, { data ->
                favoriteMovieAdapter.submitList(data)
            })
        }

        with(binding.rvMovies) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = favoriteMovieAdapter
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)


        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if(view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val favoriteMovieEntity = favoriteMovieAdapter.getSwipedForDelete(swipedPosition)
                favoriteMovieEntity?.let {
                    viewModel.setFavoriteMovies(it)
                }

                val snackBar = Snackbar.make(requireView(), R.string.deleted, Snackbar.LENGTH_LONG)
                snackBar.setAction(R.string.clear) { _ ->
                    favoriteMovieEntity?.let {
                        viewModel.setFavoriteMovies(it)
                    }
                }
                snackBar.show()
            }


        }

    })


}