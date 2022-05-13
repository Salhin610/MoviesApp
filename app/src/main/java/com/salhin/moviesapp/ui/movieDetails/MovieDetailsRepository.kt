package com.salhin.moviesapp.ui.movieDetails

import com.salhin.moviesapp.network.APIInterface

class MovieDetailsRepository(private val retrofitService: APIInterface) {

    fun getMovieDetails( apiKey: String, movieId: Int ) = retrofitService.getMovieDetails( movieId, apiKey)


}