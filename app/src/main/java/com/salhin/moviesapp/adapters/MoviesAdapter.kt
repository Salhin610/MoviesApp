package com.salhin.moviesapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.salhin.moviesapp.R
import com.salhin.moviesapp.models.MovieDetails
import com.salhin.moviesapp.ui.main.MoviesListItemClickListener

class MoviesAdapter(
    private var listOfMovies: MutableList<MovieDetails>,
    var itemClickListener: MoviesListItemClickListener
) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesAdapter.ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recycler_movie, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            itemClickListener.onMoviesItemClickListener(listOfMovies[position])
        }

        holder.movieName.text = listOfMovies[position].original_title
        holder.releaseDate.text = listOfMovies[position].release_date
        holder.rate.text = listOfMovies[position].vote_average

        if (listOfMovies[position].adult)
           holder.adultsTag.visibility = VISIBLE
        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w500/" + listOfMovies[position].backdrop_path)
            .into(holder.movieImage)


    }

    override fun getItemCount(): Int {
        return listOfMovies.size
    }
    fun addNewItem(newItems: List<MovieDetails>){
        listOfMovies.addAll(newItems)

        notifyDataSetChanged()
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieName: TextView = itemView.findViewById(R.id.movie_name)
        val releaseDate: TextView = itemView.findViewById(R.id.release_date)
        val rate: TextView = itemView.findViewById(R.id.rate)
        val adultsTag: TextView = itemView.findViewById(R.id.adults_tag)
        val movieImage: ImageView = itemView.findViewById(R.id.movie_image)
    }

}