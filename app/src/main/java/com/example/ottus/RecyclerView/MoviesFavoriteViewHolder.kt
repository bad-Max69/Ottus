package com.example.ottus.RecyclerView

import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ottus.Model.Network.ResultsItem
import com.example.ottus.R
import kotlin.random.Random

class MoviesFavoriteViewHolder (item: View): RecyclerView.ViewHolder (item) {
   private val random = Random.nextInt(150, 200)
    val imageFilm = item.findViewById<ImageView>(R.id.imageFilmFavoriteItem)
    var checkFavourite: CheckBox = item.findViewById<CheckBox>(R.id.checkFavoriteMovies)
    lateinit var title: String


    fun bind (item: ResultsItem){

        title = item.title!!

        //обработчик изображений, т.к. по уомлчанию загружается полная картинка
        Glide.with(imageFilm.context).load(item.getPosterPath).into(imageFilm)
        imageFilm.layoutParams.height = (random* (checkFavourite.context.resources.displayMetrics.density)).toInt()
        //Log.e("glide", item.getPosterPath)
        //imageFilm.setImageResource(item.imageFilm)
        //checkFavourite.isChecked = item in Movies.favoriteSet

        imageFilm.transitionName = item.getPosterPath.toString()

    }


}
