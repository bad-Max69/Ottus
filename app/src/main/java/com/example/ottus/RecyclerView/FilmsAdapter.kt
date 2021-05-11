package com.example.ottus.RecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ottus.R

class FilmsAdapter(private val inflater: LayoutInflater, private val filmList: MutableList<FilmsItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == HEADER_VIEW_TYPE)
            HeaderViewHolder(inflater.inflate(R.layout.item_header, parent, false))
        else
            FilmsViewHolder(inflater.inflate(R.layout.recycler_films_item, parent, false))

    }

    override fun getItemCount() = filmList.size + 1 // в список элементов добавляется Header

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) HEADER_VIEW_TYPE else ITEM_VIEW_Type
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       if (holder is FilmsViewHolder) {
           holder.bind(filmList[position-1])
           holder.checkFafor.setOnCheckedChangeListener(null)
           holder.checkFafor.isChecked = filmList[position-1].favorite
           holder.checkFafor.setOnCheckedChangeListener{
                   _, isChecked ->  filmList[position-1].favorite = isChecked


           }

          }


    }


    companion object {
        const val ITEM_VIEW_Type = 0
        const val HEADER_VIEW_TYPE = 1

    }
}

