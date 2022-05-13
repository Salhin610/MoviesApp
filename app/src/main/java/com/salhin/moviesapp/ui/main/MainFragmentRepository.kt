package com.salhin.moviesapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.salhin.moviesapp.network.APIInterface

class MainFragmentRepository(private val retrofitService: APIInterface)  {


    fun getMovies( apiKey: String, page: Int ) = retrofitService.getMovies( apiKey, page)

}