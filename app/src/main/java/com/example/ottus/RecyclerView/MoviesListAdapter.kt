package com.example.ottus.RecyclerView

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.ottus.Model.Network.ResultsItem
import com.example.ottus.Model.Repo.Movies
import com.example.ottus.Model.Repo.Movies.favoriteSet
import com.example.ottus.R
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

class MoviesListAdapter(

   // private val context: Context,
    private val inflater: LayoutInflater,
    private val filmList: MutableList<ResultsItem>,
    private val listener: ((filmItem: ResultsItem, sharedTitle: View, sharedSubTitle: View, sharedImage: View) -> Unit)?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {



    fun insertDataMovies()=
        notifyItemRangeInserted(
            Movies.moviesTMDB.size - 1,
            Movies.moviesTMDB.size + 20)


    fun removeMovieFromFavoriteList(positionRemove: Int) = notifyItemRemoved(positionRemove)

    fun returnDeleteMovie(positionReturn: Int) = notifyItemInserted(favoriteSet.size-2)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == HEADER_VIEW_TYPE)
            FooterViewHolder(inflater.inflate(R.layout.item_footer, parent, false))
        else
            FilmsViewHolder(inflater.inflate(R.layout.recycler_films_item, parent, false))

    }



    override fun getItemCount() = filmList.size + 1 // в список элементов добавляется Header

    override fun getItemViewType(position: Int): Int {
        return if (position == filmList.size) HEADER_VIEW_TYPE else ITEM_VIEW_Type
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FilmsViewHolder) {
            holder.bind(filmList[position])
            holder.itemView.setOnClickListener {
                listener?.invoke(
                    filmList[position],
                    holder.title,
                    holder.subTitle,
                    holder.imageFilm
                )
                Log.e("Listner", "FilmAdapter ${listener.toString()}")


            }

            //

            //  holder.checkFavourite.setChecked(filmList[position-1].favorite)
            holder.checkFavourite.setOnCheckedChangeListener { _, isChecked ->
                favoriteSet.add(filmList[position])

                //снэкбар при удалении
                if (!isChecked) {

                    val bufferItem = filmList[position]

                    // filmList[position-1].favorite = true
                    favoriteSet.remove(filmList[position])
                    removeMovieFromFavoriteList(position)


                    Snackbar.make(
                        (holder.title.context as Activity).findViewById<CoordinatorLayout>(R.id.coordinatorLayoutMain),
                        "Удалить избранный фильм: \"${holder.title.text.toString().capitalize()}\"?",
                        Snackbar.LENGTH_LONG
                    )
                        .setAnchorView((holder.title.context as Activity).findViewById<CoordinatorLayout>(R.id.bottom_navigation))
                        .setAction("отменить") {
                            holder.checkFavourite.isChecked = true
                            favoriteSet.add(bufferItem)
                            returnDeleteMovie(position)

                        }
                        .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE)
                        .show()
                }

            }

        }
    }


    companion object {
        const val ITEM_VIEW_Type = 0
        const val HEADER_VIEW_TYPE = 1

    }
}


