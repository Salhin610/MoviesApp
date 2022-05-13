package com.salhin.moviesapp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.salhin.moviesapp.models.MovieDetails
import com.salhin.moviesapp.models.MoviesResponse
import com.salhin.moviesapp.network.APIInterface.Companion.apiKey
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val repository: MainFragmentRepository) : ViewModel() {


     var listOfMovies: MutableLiveData<MutableList<MovieDetails>> = MutableLiveData()
     var totalPages: MutableLiveData<Int> = MutableLiveData()
     var showToastMessage: MutableLiveData<String> = MutableLiveData()

    fun getMovies( page: Int) {

        val response = repository.getMovies(apiKey, page)
        response.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                listOfMovies.postValue(response.body()?.results)
                totalPages.postValue(response.body()?.total_pages)
            }
            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                showToastMessage.postValue(t.message)
            }
        })
    }

}