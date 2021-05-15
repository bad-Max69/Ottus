package com.example.ottus

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.ottus.Films.filmFavorite
import com.example.ottus.Films.filmList
import com.example.ottus.Fragment.FilmDetailedFragment
import com.example.ottus.Fragment.FilmListFragment
import com.example.ottus.Fragment.FilmsFavoriteFragment
import com.example.ottus.RecyclerView.FilmsItem
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity :
    AppCompatActivity(),
    FilmListFragment.OnFilmsClickListener,
    FilmsFavoriteFragment.OnFilmsFavoriteClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_for_fragment)


        makeCurrentFragment(FilmListFragment())

        //создание и работа с меню навигации
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.page_1 -> makeCurrentFragment(FilmListFragment())
                R.id.page_2 -> {
                    filmFavorite = filmList.filter { it.favorite }.toMutableList()
                    makeCurrentFragment(FilmsFavoriteFragment())

                }
            }
            true
        }
        // отключение повторного нажатия на выбранный элемент меню навигации
        bottomNavigationView.setOnNavigationItemReselectedListener { _ -> Unit }

    }

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

    private fun openFilmDetailedFragment(item: FilmsItem, sharedTitle: View, sharedSubTitle: View, sharedImage: View) {

        val sharedElementFragment = FilmDetailedFragment.newInstance(item).apply {
            sharedElementEnterTransition = TransitionInflater.from(this@MainActivity)
                .inflateTransition(R.transition.simple_transition)
        }

        supportFragmentManager
            .beginTransaction()
            //.addSharedElement(sharedTitle, "sharedTitle")
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

    override fun onFilmClick(item: FilmsItem, sharedTitle: View, sharedSubTitle: View, sharedImage: View) {
        openFilmDetailedFragment(item, sharedTitle, sharedSubTitle, sharedImage)
    }

    override fun onFilmClickFavorite(item: FilmsItem, sharedTitle: View, sharedSubTitle: View, sharedImage: View) {
        openFilmDetailedFragment(item, sharedTitle, sharedSubTitle, sharedImage)
    }
}


