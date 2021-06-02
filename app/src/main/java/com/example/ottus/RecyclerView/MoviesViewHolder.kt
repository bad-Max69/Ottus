package com.example.ottus.RecyclerView

import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ottus.Model.Network.ResultsItem
import com.example.ottus.R

class MoviesViewHolder (item: View): RecyclerView.ViewHolder (item) {


    val title  = item.findViewById<TextView>(R.id.titleFilmItem)
    val subTitle: TextView = item.findViewById<TextView>(R.id.subTitleFilmItem)
    val imageFilm = item.findViewById<ImageView>(R.id.imageFilmFavoriteItem)
    var checkFavourite: CheckBox = item.findViewById<CheckBox>(R.id.check_favor)
    var rate = item.findViewById<TextView>(R.id.rateView)


    fun bind (item: ResultsItem){



        title.text = item.title
        subTitle.text = item.overview
        rate.text = item.popularity?.toInt().toString()

        //обработчик изображений, т.к. по уомлчанию загружается полная картинка
        Glide.with(title.context).load(item.getPosterPath).into(imageFilm)
        //Log.e("glide", item.getPosterPath)
        //imageFilm.setImageResource(item.imageFilm)
        //checkFavourite.isChecked = item in Movies.favoriteSet

        title.transitionName = item.title
        subTitle.transitionName = item.overview
        imageFilm.transitionName = item.getPosterPath.toString()

    }


}
