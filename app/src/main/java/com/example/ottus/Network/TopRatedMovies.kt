package com.example.ottus.Network

import android.util.Log
import com.example.ottus.Films
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object TopRatedMovies {

    private const val API_KEY = "e6cb68048343238dadf3afda6a0f928f"

    var responseTMDB = false
   private var pages = 1


    //retrofit
    // делаем запрос в ТМДБ

    public  fun getTopRatedMovies() {


        val callTopRatedMovies = MovieApiClient.apiClient
            .getTopRatedMoviesI(API_KEY, "ru", pages)


        // Получаем результат
        callTopRatedMovies.enqueue(object : Callback<MoviesResponse> {

            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                response.body()?.results?.let { Films.moviesTMDB.addAll(it) }
                responseTMDB = response.isSuccessful
                pages++

               /* Films.moviesTMDB.forEach { movie -> Log.e("movies", movie?.getPosterPath.orEmpty()) }
                Log.e("StartApp", "getDataTMDB")*/
            }


            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                // Log error here since request failed
                Log.e("failData", t.toString())
            }
        })

    }










}