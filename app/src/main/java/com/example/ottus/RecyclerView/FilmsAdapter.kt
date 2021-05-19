package com.example.ottus.RecyclerView

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.ottus.Films.favoriteSet
import com.example.ottus.Network.ResultsItem
import com.example.ottus.R
import com.google.android.material.snackbar.Snackbar

class FilmsAdapter(
    private val context: Context,
    private val inflater: LayoutInflater,
    private val filmList: MutableList<ResultsItem?>,
    private val listener: ((filmItem: ResultsItem, sharedTitle: View, sharedSubTitle: View, sharedImage: View) -> Unit)?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == HEADER_VIEW_TYPE)
            HeaderViewHolder(inflater.inflate(R.layout.item_header, parent, false))
        else
            FilmsViewHolder(context, inflater.inflate(R.layout.recycler_films_item, parent, false))

    }

    override fun getItemCount() = filmList.size + 1 // в список элементов добавляется Header

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) HEADER_VIEW_TYPE else ITEM_VIEW_Type
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       if (holder is FilmsViewHolder) {
           filmList[position-1]?.let { holder.bind(it) }
           holder.itemView.setOnClickListener { filmList[position-1]?.let { it1 ->
               listener?.invoke(
                   it1, holder.title, holder.subTitle, holder.imageFilm)
           } }

          // holder.checkFavourite.setOnCheckedChangeListener(null)

         //  holder.checkFavourite.setChecked(filmList[position-1].favorite)
           holder.checkFavourite.setOnCheckedChangeListener{
               _, isChecked ->  filmList[position-1]?.let { favoriteSet.add(it) }

               //снэкбар при удалении
               if (!isChecked) {

                   Snackbar.make(
                       (context as Activity).findViewById<CoordinatorLayout>(R.id.coordinatorLayoutMain),
                       "удален фильм: \"${holder.title.text}\"",
                       Snackbar.LENGTH_LONG
                   )
                       .setAnchorView((context as Activity).findViewById<CoordinatorLayout>(R.id.bottom_navigation))
                       .setAction("отменить"){
                           holder.checkFavourite.isChecked = true
                          // filmList[position-1].favorite = true
                       }
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


