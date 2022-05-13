package com.salhin.moviesapp.ui.movieDetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.salhin.moviesapp.databinding.FragmentMovieDetailsBinding
import com.salhin.moviesapp.hideProgressDialog
import com.salhin.moviesapp.models.MovieDetails
import com.salhin.moviesapp.network.APIInterface
import com.salhin.moviesapp.showProgressDialog
import com.salhin.moviesapp.utils.Constants

class MovieDetailsFragment : Fragment(), Constants {

    private lateinit var viewModel: MovieDetailsViewModel
    private val retrofitService = APIInterface.getInstance()

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    private var movieId: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this, MovieDetailsViewModelFactory(MovieDetailsRepository(retrofitService)))[MovieDetailsViewModel::class.java]

        viewModel = ViewModelProvider(this)[MovieDetailsViewModel::class.java]
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        movieId = arguments?.getInt(bundleMovieId,0)!!
        context?.let { showProgressDialog(it) }
        viewModel.getMovieDetails(movieId)
        observers()
        actions()
        return root

    }

    private fun observers() {
        viewModel.movieDetails.observe(viewLifecycleOwner, Observer {
            hideProgressDialog()
            fillData(it)
        })

        viewModel.showToastMessage.observe(viewLifecycleOwner, Observer {
            hideProgressDialog()
            Toast.makeText(context,it, Toast.LENGTH_LONG).show()
        })
    }

    private fun fillData(movieDetails: MovieDetails?) {

        binding.movieName.text = movieDetails?.original_title
        binding.releaseDate.text = movieDetails?.release_date
        context?.let { Glide.with(it).load(imagesBaseUrl + movieDetails?.poster_path).into(binding.movieImage) }
        binding.movieDescription.text = movieDetails?.overview
        binding.movieStatus.text = movieDetails?.status
    }

    private fun actions() {

    }

}