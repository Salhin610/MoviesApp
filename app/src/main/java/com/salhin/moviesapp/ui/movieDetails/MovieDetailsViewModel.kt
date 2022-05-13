package com.salhin.moviesapp.ui.movieDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.salhin.moviesapp.models.MovieDetails
import com.salhin.moviesapp.network.APIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailsViewModel(private val repository: MovieDetailsRepository) : ViewModel() {


    var movieDetails: MutableLiveData<MovieDetails> = MutableLiveData()
    var showToastMessage: MutableLiveData<String> = MutableLiveData()

    fun getMovieDetails( movieId: Int) {

        val response = repository.getMovieDetails(APIInterface.apiKey,movieId)
        response.enqueue(object : Callback<MovieDetails> {
            override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {
                movieDetails.postValue(response.body())
            }
            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                showToastMessage.postValue(t.message)
            }
        })
    }
}