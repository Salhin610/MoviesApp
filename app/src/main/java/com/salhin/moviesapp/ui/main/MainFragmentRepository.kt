package com.salhin.moviesapp.ui.main


import com.salhin.moviesapp.network.APIInterface

class MainFragmentRepository(private val retrofitService: APIInterface)  {


    fun getMovies( apiKey: String, page: Int ) = retrofitService.getMovies( apiKey, page)

}