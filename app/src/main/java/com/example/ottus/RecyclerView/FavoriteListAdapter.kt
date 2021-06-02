package com.example.ottus.RecyclerView

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.ottus.Model.Network.ResultsItem
import com.example.ottus.Model.Repo.Movies
import com.example.ottus.R
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

class FavoriteListAdapter(
    private val inflater: LayoutInflater,
    private val favoriteMovieList: MutableList<ResultsItem>,
    private val listner: ((movieItem: ResultsItem, sharedImage: View) -> Unit)?

): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MoviesFavoriteViewHolder(inflater.inflate(R.layout.item_favorite_movie_recycler, parent, false))
    }

    fun removeMovieFromFavoriteList(positionRemove: Int) = notifyItemRemoved(positionRemove)

    fun returnDeleteMovie(positionReturn: Int) = notifyItemInserted(positionReturn-1)




    override fun getItemCount(): Int = favoriteMovieList.size

    @SuppressLint("DefaultLocale")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       if (holder is MoviesFavoriteViewHolder){
           holder.bind(favoriteMovieList[position])

           holder.checkFavourite.isChecked = true

           holder.itemView.setOnClickListener {
               listner?.invoke(favoriteMovieList[position],
               holder.imageFilm)
           }

               holder.checkFavourite.setOnCheckedChangeListener{_, isChecked ->
                   //снэкбар при удалении
                   if (!isChecked) {

                       val bufferItem = favoriteMovieList[position]

                       // filmList[position-1].favorite = true
                       Movies.favoriteSet.remove(favoriteMovieList[position])
                       removeMovieFromFavoriteList(position)


                       Snackbar.make(
                           (holder.imageFilm.context as Activity).findViewById<CoordinatorLayout>(R.id.coordinatorLayoutMain),
                           "Удален избранный фильм: \"${holder.title.capitalize()}",
                           Snackbar.LENGTH_LONG
                       )
                           .setAnchorView((holder.itemView.context as Activity).findViewById<CoordinatorLayout>(R.id.bottom_navigation))
                           .setAction("отменить") {
                               holder.checkFavourite.isChecked = true
                               Movies.favoriteSet.add(bufferItem)

                               //returnDeleteMovie(position)

                           }
                           .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE)
                           .show()
                   }

               }



           }

       }
    }


