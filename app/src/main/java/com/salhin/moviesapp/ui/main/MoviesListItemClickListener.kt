package com.salhin.moviesapp.ui.main

import com.salhin.moviesapp.models.MovieDetails

interface MoviesListItemClickListener {
    fun onMoviesItemClickListener(clickedMovie: MovieDetails)

}