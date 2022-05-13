package com.salhin.moviesapp.models

data class MoviesResponse(val total_pages : Int, val results : MutableList<MovieDetails>)
