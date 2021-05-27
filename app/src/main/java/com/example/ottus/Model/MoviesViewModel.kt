package com.example.ottus.Model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ottus.Model.Network.ResultsItem
import com.example.ottus.Model.Network.TopRatedMoviesInterractor
import com.example.ottus.Model.Repo.Movies
import com.example.ottus.Model.Repo.Movies.moviesTMDB

class MoviesViewModel :ViewModel() {
    private val moviesRepoLiveData = MutableLiveData<MutableList<ResultsItem>>()
    private val moviesFavoriteLiveData = MutableLiveData<MutableSet<ResultsItem>>()


    val moviesRepo: LiveData<MutableList<ResultsItem>>
        get() = moviesRepoLiveData

    val moviesFavorite: LiveData<MutableSet<ResultsItem>>
        get() = moviesFavoriteLiveData

    init {
     moviesRepoLiveData.postValue(moviesTMDB)

        Log.e("Live", "init livedata ${moviesTMDB.size}")

     moviesFavoriteLiveData.postValue(Movies.favoriteSet)
    }

fun getMoviesForView(){
    TopRatedMoviesInterractor.getTopRatedMovies()
    moviesRepoLiveData.postValue(moviesTMDB)

}



}