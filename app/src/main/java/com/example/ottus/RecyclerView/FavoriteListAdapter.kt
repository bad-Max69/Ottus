package com.example.ottus.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ottus.Model.Network.ResultsItem

class FavoriteListAdapter(
    private val inflater: LayoutInflater,
    private val favoriteMovieList: MutableList<ResultsItem>,
    private val listner: ((movieItem: ResultsItem, sharedImage: View) -> Unit)?

): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}