package com.example.ottus.Model.Network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface MovieApiInterface {
    @GET("movie/top_rated")
    fun getTopRatedMoviesI(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<MoviesResponse>
}
