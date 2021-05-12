package com.example.ottus

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.ottus.Films.filmFavorite
import com.example.ottus.Films.filmList
import com.example.ottus.Fragment.FilmDetailedFragment
import com.example.ottus.Fragment.FilmListFragment
import com.example.ottus.Fragment.FilmsFavoriteFragment
import com.example.ottus.RecyclerView.FilmsItem


class MainActivity :
        AppCompatActivity(),
        FilmListFragment.OnFilmsClickListener,
        FilmsFavoriteFragment.OnFilmsFavoriteClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_for_fragment)


        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, FilmListFragment(), "FilmListFragment")
                .commit()


        findViewById<Button>(R.id.fragment_buttonViewFavorite).setOnClickListener {
            filmFavorite = filmList.filter { it.favorite }.toMutableList()
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, FilmsFavoriteFragment(), "FilmsFavoriteFragment")
                    .addToBackStack(null)
                    .commit()
        }


    }


    override fun onAttachFragment(fragment: Fragment) {
       // super.onAttachFragment(fragment)

        when (fragment) {
            is FilmListFragment -> fragment.listener = this
            is FilmsFavoriteFragment -> fragment.listenerFavoriteFragment = this
        }
    }

    private fun openFilmDetailedFragment(item: FilmsItem){

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, FilmDetailedFragment.newInstance(item), "FilmsFavoriteFragment")
                .addToBackStack(null)
                .commit()

    }

    override fun onFilmClick(item: FilmsItem) {
        openFilmDetailedFragment(item)
    }

    override fun onFilmClickFavorite(item: FilmsItem) {
        openFilmDetailedFragment(item)
    }

}


