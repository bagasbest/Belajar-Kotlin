package com.bagasbest.beoskop21.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bagasbest.beoskop21.databinding.FragmentMoviesBinding
import com.bagasbest.beoskop21.model.source.remote.response.ItemList
import com.bagasbest.beoskop21.viewmodel.adapter.MoviesAdapter
import com.bagasbest.beoskop21.viewmodel.viewmodel.MoviesViewModel
import com.bagasbest.beoskop21.viewmodel.viewmodel.ViewModelFactory

class MoviesFragment : Fragment() {

    private lateinit var binding: FragmentMoviesBinding
    private var movie = listOf<ItemList>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            binding.progressBar.visibility = View.VISIBLE
            val factory = activity?.application?.let {
                ViewModelFactory.getInstance()
            }
            val viewModel =
                factory?.let { ViewModelProvider(this, it) }?.get(MoviesViewModel::class.java)

            val moviesAdapter = MoviesAdapter()

            binding.progressBar.visibility = View.VISIBLE
            viewModel?.movie?.observe(viewLifecycleOwner, {
                binding.progressBar.visibility = View.GONE
                movie = it
                moviesAdapter.setData(movie)
            })

            with(binding.rvMovies) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = moviesAdapter
            }
        }
    }

}