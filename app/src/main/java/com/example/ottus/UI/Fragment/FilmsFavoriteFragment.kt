package com.example.ottus.UI.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.ottus.Model.MoviesViewModel
import com.example.ottus.Model.Network.ResultsItem
import com.example.ottus.R
import com.example.ottus.RecyclerView.FilmsAdapter
import jp.wasabeef.recyclerview.animators.ScaleInRightAnimator

class FilmsFavoriteFragment : Fragment() {
    private val moviesViewModel: MoviesViewModel by viewModels()
    private var recyclerView: RecyclerView? = null
    private var adapter: FilmsAdapter? = null
    var listenerFavoriteFragment: OnFilmsFavoriteClickListener? = null



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_films_recycler, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //super.onViewCreated(view, savedInstanceState)
         initFavoriteRecycler()

    }

    private fun initFavoriteRecycler() {


        recyclerView = view?.findViewById<RecyclerView>(R.id.fragment_recyclerView_favorite)

        // adapter = FilmsAdapter(LayoutInflater.from(context), filmFavorite){ listenerFavoriteFragment?.onFilmClick(it)}
        adapter = FilmsAdapter(
            LayoutInflater.from(context),
            moviesViewModel.moviesFavorite.value!!
        ) { item, sharedTitle, sharedSubTitle, sharedImage ->
            listenerFavoriteFragment?.onFilmClickFavorite(
                item,
                sharedTitle,
                sharedSubTitle,
                sharedImage
            )
        }

        recyclerView?.adapter = adapter

        recyclerView?.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        recyclerView?.itemAnimator = ScaleInRightAnimator()

        Log.e("fragmentVeiw favorite", "onCreated")


    }


    interface OnFilmsFavoriteClickListener {
        fun onFilmClickFavorite(
            item: ResultsItem,
            sharedTitle: View,
            sharedSubTitle: View,
            sharedImage: View
        )

    }

}



