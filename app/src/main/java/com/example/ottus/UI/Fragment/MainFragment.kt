package com.example.ottus.UI.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.example.ottus.Model.Network.ResultsItem
import com.example.ottus.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainFragment : Fragment(),
    FilmListFragment.OnFilmsClickListener,
    FilmsFavoriteFragment.OnFilmsFavoriteClickListener
{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)



    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        makeCurrentFragment(FilmListFragment())

        val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigationView?.setOnNavigationItemSelectedListener { item ->
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
        bottomNavigationView?.setOnNavigationItemReselectedListener { _ -> Unit }

    }

  /*  override fun restoreChildFragmentState(savedInstanceState: Bundle?) {
        super.restoreChildFragmentState(savedInstanceState)
    }*/



    private fun makeCurrentFragment(fragment: Fragment) {
      childFragmentManager.beginTransaction()
          .replace(R.id.fragment_container_Main, fragment)
          .commit()

    }



    private fun openFilmDetailedFragment (
        item: ResultsItem,
        sharedTitle: View,
        sharedSubTitle: View,
        sharedImage: View
    ) {
        val sharedElementFragment = FilmDetailedFragment.newInstance(item).apply {
        sharedElementEnterTransition = TransitionInflater.from(context)
            .inflateTransition(R.transition.simple_transition)
    }

        childFragmentManager
            .beginTransaction()
            .addSharedElement(sharedTitle, "sharedTitle")
            //.addSharedElement(sharedSubTitle, "sharedSubTitle")
            .addSharedElement(sharedImage, "sharedImage")
            .replace(
                R.id.fragment_container_Main,
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
          Log.e("Listner", "override onFilm in MainFrag")
  }

  override fun onFilmClickFavorite(
      item: ResultsItem,
      sharedTitle: View,
      sharedSubTitle: View,
      sharedImage: View
  ) {
      openFilmDetailedFragment(item, sharedTitle, sharedSubTitle, sharedImage)
  }






}