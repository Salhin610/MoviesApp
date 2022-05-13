package com.salhin.moviesapp.network

import com.google.gson.GsonBuilder
import com.salhin.moviesapp.models.MovieDetails
import com.salhin.moviesapp.models.MoviesResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface APIInterface  {

    companion object {
        val apiKey: String = "c9856d0cb57c3f14bf75bdc6c063b8f3"
        var retrofitService: APIInterface? = null
        var baseUrl: String = "https://api.themoviedb.org/3/"
        fun getInstance(): APIInterface {
            if (retrofitService == null) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                val client: OkHttpClient = OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(interceptor).build()

                val gson = GsonBuilder()
                    .setLenient()
                    .create()

                val retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build()
                retrofitService = retrofit.create(APIInterface::class.java)
            }
            return retrofitService!!
        }
    }



    @GET("discover/movie")
    fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int,
    )
            : Call<MoviesResponse>


    @GET("movie/{movieId}")
    fun getMovieDetails(
        @Path("movieId") movieId: Int,
        @Query("api_key") apiKey: String,
    )
            : Call<MovieDetails>



}