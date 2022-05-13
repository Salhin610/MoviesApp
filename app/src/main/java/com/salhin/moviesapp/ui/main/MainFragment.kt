package com.salhin.moviesapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.salhin.moviesapp.R
import com.salhin.moviesapp.adapters.MoviesAdapter
import com.salhin.moviesapp.databinding.MainFragmentBinding
import com.salhin.moviesapp.hideProgressDialog
import com.salhin.moviesapp.models.MovieDetails
import com.salhin.moviesapp.network.APIInterface
import com.salhin.moviesapp.showProgressDialog
import com.salhin.moviesapp.utils.Constants

class MainFragment : Fragment(), MoviesListItemClickListener, Constants {


    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel
    private lateinit var navController: NavController
    private val retrofitService = APIInterface.getInstance()
    lateinit var moviesItemClickListener: MoviesListItemClickListener
    private  var currentListOfMovies : List<MovieDetails> = emptyList()

    private var page: Int = 1
    private var totalPages: Int = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this, MainFragmentViewModelFactory(MainFragmentRepository(retrofitService)))[MainViewModel::class.java]

        _binding = MainFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        navController = NavHostFragment.findNavController(this)
        moviesItemClickListener = this
        if (currentListOfMovies.isEmpty())
            getMovies()
        else{
            binding.moviesRv.layoutManager = LinearLayoutManager(context)
            binding.moviesRv.adapter = adapter
        }
        observers();
        actions()
        return root
    }

    private fun actions() {
        binding.nestedScroll.setOnScrollChangeListener { v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            run {
                if (v != null) {
                    if (scrollY == v?.getChildAt(0)?.measuredHeight?.minus(v.measuredHeight)) {
                        if (page < totalPages) {
                            page++;
                            getMovies()
                        }
                    }
                }
            }
        }


    }
    private lateinit var adapter: MoviesAdapter

    private fun observers(){
        viewModel.listOfMovies.observe(viewLifecycleOwner) {
            hideProgressDialog()
            if (currentListOfMovies.isEmpty()){
                currentListOfMovies = it
                adapter = MoviesAdapter(it, moviesItemClickListener)

                binding.moviesRv.layoutManager = LinearLayoutManager(context)
                binding.moviesRv.adapter = adapter
            }else
                adapter.addNewItem(it)
        }
        viewModel.showToastMessage.observe(viewLifecycleOwner, Observer {
            hideProgressDialog()
            Toast.makeText(context,it,LENGTH_LONG).show()
        })
        viewModel.totalPages.observe(viewLifecycleOwner) {
            totalPages = it
        }
    }
    private fun getMovies() {
        context?.let { showProgressDialog(it) }
        viewModel.getMovies(page)
    }

    override fun onMoviesItemClickListener(clickedMovie: MovieDetails) {
        var movieDetails = Bundle()
        movieDetails.putInt(movieId, clickedMovie.id)

        navController.navigate(
            R.id.action_mainFragment_to_movieDetailsFragment,
            movieDetails,
            NavOptions.Builder()
                .setEnterAnim(android.R.animator.fade_in)
                .setExitAnim(android.R.animator.fade_out)
                .build()
        )
    }
}