package com.salhin.moviesapp.ui.movieDetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.salhin.moviesapp.databinding.FragmentMovieDetailsBinding
import com.salhin.moviesapp.utils.Constants

class MovieDetailsFragment : Fragment(), Constants {

    private lateinit var viewModel: MovieDetailsViewModel

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this)[MovieDetailsViewModel::class.java]
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        Log.e("MovieId", arguments?.getInt(movieId,0).toString())
        return root

    }

}