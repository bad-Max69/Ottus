package com.example.ottus

import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.ottus.Fragment.FilmDetailedFragment
import com.example.ottus.Fragment.FilmListFragment
import com.example.ottus.Fragment.FilmsFavoriteFragment
import com.example.ottus.Network.ResultsItem
import com.example.ottus.Network.TopRatedMovies
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity :
    AppCompatActivity(),
    FilmListFragment.OnFilmsClickListener,
    FilmsFavoriteFragment.OnFilmsFavoriteClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_for_fragment)

        Log.e("StartApp", " OnCreate")
        TopRatedMovies.getTopRatedMovies()



        makeCurrentFragment(FilmListFragment())





        //создание и работа с меню навигации
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)


        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.page_1 -> makeCurrentFragment(FilmListFragment())
                R.id.page_2 -> makeCurrentFragment(FilmsFavoriteFragment())
            }
            true
        }

        // отключение повторного нажатия на выбранный элемент меню навигации
        bottomNavigationView.setOnNavigationItemReselectedListener { _ -> Unit }

    }

    override fun onStart() {
        super.onStart()

        Log.e("StartApp", "FilmListFragmentOnStart")

    }


    /*//retrofit
    // делаем запрос в ТМДБ
    private fun getTopRatedMovies() {

        var responseTMDB = false
        var pages = 1

        val callTopRatedMovies = MovieApiClient.apiClient
            .getTopRatedMoviesI(API_KEY, "ru", pages)
        Log.e("StartApp", " OnCreate")

        // Получаем результат
        callTopRatedMovies.enqueue(object : Callback<MoviesResponse> {

            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                response.body()?.results?.let { moviesTMDB.addAll(it) }
                responseTMDB = response.isSuccessful
                pages++

                Films.moviesTMDB.forEach { movie -> Log.e("movies", movie?.getPosterPath.orEmpty()) }
                Log.e("StartApp", "getDataTMDB")
            }


            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                // Log error here since request failed
                Log.e("failData", t.toString())
            }
        })

    }
*/



    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()

    }

    override fun onAttachFragment(fragment: Fragment) {
        // super.onAttachFragment(fragment)
        when (fragment) {
            is FilmListFragment -> fragment.listener = this
            is FilmsFavoriteFragment -> fragment.listenerFavoriteFragment = this
        }
    }

    private fun openFilmDetailedFragment(
        item: ResultsItem,
        sharedTitle: View,
        sharedSubTitle: View,
        sharedImage: View
    ) {

        val sharedElementFragment = FilmDetailedFragment.newInstance(item).apply {
            sharedElementEnterTransition = TransitionInflater.from(this@MainActivity)
                .inflateTransition(R.transition.simple_transition)
        }

        supportFragmentManager
            .beginTransaction()
            .addSharedElement(sharedTitle, "sharedTitle")
            //.addSharedElement(sharedSubTitle, "sharedSubTitle")
            .addSharedElement(sharedImage, "sharedImage")
            .replace(
                R.id.fragment_container,
                sharedElementFragment,
                "FilmsFavoriteFragment"
            )
            .addToBackStack(null)
            .commit()
    }

    override fun onFilmClick(
        item: ResultsItem,
        sharedTitle: View,
        sharedSubTitle: View,
        sharedImage: View
    ) {
        openFilmDetailedFragment(item, sharedTitle, sharedSubTitle, sharedImage)
    }

    override fun onFilmClickFavorite(
        item: ResultsItem,
        sharedTitle: View,
        sharedSubTitle: View,
        sharedImage: View
    ) {
        openFilmDetailedFragment(item, sharedTitle, sharedSubTitle, sharedImage)
    }

    companion object {

        private const val API_KEY = "e6cb68048343238dadf3afda6a0f928f"
    }

}


