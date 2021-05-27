package com.example.ottus.Model.Network

import android.util.Log
import com.example.ottus.BuildConfig
import com.example.ottus.MainActivity
import com.example.ottus.Model.Repo.Movies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object TopRatedMoviesInterractor {

    private val TAG = MainActivity::class.java.simpleName
    private const val API_KEY = BuildConfig.THE_MOVIE_DATABASE_API

    var responseTMDB = false
    private var pages = 1


    //retrofit
    // делаем запрос в ТМДБ

    fun getTopRatedMovies() {

        val callTopRatedMovies = MovieApiClient.apiClient
            .getTopRatedMoviesI(API_KEY, "ru", pages)

        // Получаем результат
        callTopRatedMovies.enqueue(object : Callback<MoviesResponse> {

            override fun onResponse(
                call: Call<MoviesResponse>, response: Response<MoviesResponse>
            ) {
                if (response.isSuccessful){
                    Movies.moviesTMDB.addAll(response.body()!!.results!!)
                    responseTMDB = response.isSuccessful
                    pages++
                    Log.e("Live", "get movies from retrofit")
                } else {
                    Log.e("Live", "bad response ${response.code().toString()}")

                    response.code().toString()
                }



            }
            /* Films.moviesTMDB.forEach { movie -> Log.e("movies", movie?.getPosterPath.orEmpty()) }
             Log.e("StartApp", "getDataTMDB")*/


            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {

                // Log error here since request failed
                Log.e("failData", t.toString())
            }
        })

    }





}