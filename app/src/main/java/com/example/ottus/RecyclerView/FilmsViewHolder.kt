package com.example.ottus.RecyclerView

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ottus.Network.ResultsItem
import com.example.ottus.R

class FilmsViewHolder (var context: Context, item: View): RecyclerView.ViewHolder (item) {


    val title  = item.findViewById<TextView>(R.id.titleFilmItem)
    val subTitle = item.findViewById<TextView>(R.id.subTitleFilmItem)
    val imageFilm = item.findViewById<ImageView>(R.id.imageFilmItem)
    var checkFavourite: CheckBox = item.findViewById<CheckBox>(R.id.check_favor)


    fun bind (item: ResultsItem){
        title.text = item.title
        subTitle.text = item.overview

        //обработчик изображений, т.к. по уомлчанию загружается полная картинка
        Glide.with(context).load(item.getPosterPath).into(imageFilm)
        Log.e("glide", item.getPosterPath)
        //imageFilm.setImageResource(item.imageFilm)
       // checkFavourite.isChecked = item.favorite

        title.transitionName = item.title
        subTitle.transitionName = item.overview
        imageFilm.transitionName = item.getPosterPath.toString()

    }


}
