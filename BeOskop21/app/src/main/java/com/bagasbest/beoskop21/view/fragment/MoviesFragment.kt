package com.bagasbest.beoskop21.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bagasbest.beoskop21.databinding.FragmentMoviesBinding
import com.bagasbest.beoskop21.model.source.local.entity.MovieEntity
import com.bagasbest.beoskop21.model.utils.SortUtils
import com.bagasbest.beoskop21.model.vo.Status
import com.bagasbest.beoskop21.viewmodel.adapter.MoviesAdapter
import com.bagasbest.beoskop21.viewmodel.viewmodel.MoviesViewModel
import com.bagasbest.beoskop21.viewmodel.viewmodel.ViewModelFactory

class MoviesFragment : Fragment(), MoviesAdapter.OnItemClickCallBack{

    private lateinit var binding: FragmentMoviesBinding
    private lateinit var viewModel: MoviesViewModel
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        initViewModel()
        return binding.root
    }

    private fun initViewModel() {
        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this, factory)[MoviesViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            moviesAdapter = MoviesAdapter()
            context?.let {
                viewModel.movie(SortUtils.NEWEST).observe(viewLifecycleOwner, { data ->
                    if (data != null) {
                        when (data.status) {
                            Status.LOADING -> binding.progressBar.visibility = View.VISIBLE
                            Status.SUCCESS -> {
                                binding.progressBar.visibility = View.GONE
                                moviesAdapter.submitList(data.data)
                                moviesAdapter.setOnItemClickCallBack(this)
                                moviesAdapter.notifyDataSetChanged()
                            }
                            Status.ERROR -> {
                                binding.progressBar.visibility = View.GONE
                                Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    }
                })
            }

            with(binding.rvMovies) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = moviesAdapter
            }
        }
    }

    override fun clickFavoriteButton(movie: MovieEntity) {
        Log.d("CheckTitle", movie.title.toString())
        Log.d("CekFav", movie.isFavorite.toString())

        if(movie.isFavorite) {
            viewModel.setFavoriteMovie(movie, false)
        } else {
            viewModel.setFavoriteMovie(movie, true)
        }
    }


}