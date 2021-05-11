package com.example.ottus.RecyclerView

import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ottus.R

class FilmsViewHolder (item: View): RecyclerView.ViewHolder (item) {
   private val title: TextView = item.findViewById(R.id.titleFilmItem)
   private val subTitle = item.findViewById<TextView>(R.id.subTitleFilmItem)
   private val imageFilm = item.findViewById<ImageView>(R.id.imageFilmItem)
    var checkFafor: CheckBox = item.findViewById<CheckBox>(R.id.check_favor)


    fun bind (item: FilmsItem){
        title.text = item.title
        subTitle.text = item.subtitle
        imageFilm.setImageResource(item.imageFilm)
        checkFafor.isChecked = item.favorite

    }


}
