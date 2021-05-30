package com.example.ottus.Model

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ottus.Model.Network.ResultsItem
import com.example.ottus.Model.Network.TopRatedMoviesInterractor
import com.example.ottus.Model.Repo.Movies

class MoviesViewModel : ViewModel() {
    private val moviesRepoLiveData = MutableLiveData<MutableList<ResultsItem>>(Movies.moviesTMDB)
    private val moviesFavoriteLiveData =
        MutableLiveData<MutableList<ResultsItem>>(Movies.favoriteSet.toMutableList())


    val moviesRepo: LiveData<MutableList<ResultsItem>>
        get() = moviesRepoLiveData

    val moviesFavorite: LiveData<MutableList<ResultsItem>>
        get() = moviesFavoriteLiveData


    fun getMoviesForView() {
        TopRatedMoviesInterractor.getTopRatedMovies(object :
            TopRatedMoviesInterractor.GetRepoCallback {
            override fun onSucces(moviesRepos: MutableList<ResultsItem>) {
                moviesRepoLiveData.value = moviesRepos
            }
        })
        //moviesRepoLiveData.postValue(moviesTMDB)

    }







}