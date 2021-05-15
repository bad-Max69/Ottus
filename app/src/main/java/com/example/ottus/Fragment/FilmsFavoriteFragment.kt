package com.example.ottus.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.ottus.Films
import com.example.ottus.R
import com.example.ottus.RecyclerView.FilmsAdapter
import com.example.ottus.RecyclerView.FilmsItem
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import jp.wasabeef.recyclerview.animators.ScaleInRightAnimator

class FilmsFavoriteFragment : Fragment() {

    var listenerFavoriteFragment: FilmListFragment.OnFilmsClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_films_recycler, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.fragment_recyclerView_favorite).apply {

            // adapter = FilmsAdapter(LayoutInflater.from(context), filmFavorite){ listenerFavoriteFragment?.onFilmClick(it)}
            adapter = ScaleInAnimationAdapter(
                FilmsAdapter(
                    context,
                    LayoutInflater.from(context),
                    Films.filmFavorite
                ) { item, sharedTitle, sharedSubTitle, sharedImage -> listenerFavoriteFragment?.onFilmClick(item, sharedTitle, sharedSubTitle, sharedImage) })
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            itemAnimator = ScaleInRightAnimator()

            Log.e("fragmentVeiw favorite", "onCreated")
        }


    }

    interface OnFilmsFavoriteClickListener {
        fun onFilmClickFavorite(item: FilmsItem, sharedTitle: View, sharedSubTitle: View, sharedImage: View)

    }

}