package com.example.ottus

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.ottus.Model.Network.ResultsItem
import com.example.ottus.Model.Network.TopRatedMovies
import com.example.ottus.UI.Fragment.FilmDetailedFragment
import com.example.ottus.UI.Fragment.FilmListFragment
import com.example.ottus.UI.Fragment.FilmsFavoriteFragment
import com.example.ottus.UI.Fragment.SplashDownloadFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity :
    AppCompatActivity(),
    FilmListFragment.OnFilmsClickListener,
    FilmsFavoriteFragment.OnFilmsFavoriteClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_for_fragment)

        TopRatedMovies.getTopRatedMovies()


        makeCurrentFragment(SplashDownloadFragment())


        //создание и работа с меню навигации
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)


        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.page_1 -> {
                    makeCurrentFragment(FilmListFragment())

                }
                R.id.page_2 -> {
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


    override fun onBackPressed() {
        val dialBuilder = AlertDialog.Builder(this)
        val dialI = DialogInterface.OnClickListener { _, res ->
            if (res == -1) finish()
        }

        dialBuilder.apply {
            setTitle("Правда хочешь выйти?")
            setNegativeButton("Нет, остаюсь", dialI)
            //  setNeutralButton("Later", dialI)
            setPositiveButton("Да :(", dialI)

            val dialog = dialBuilder.create()
            dialog.show()
        }


    }
}


