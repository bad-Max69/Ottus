package com.example.ottus.RecyclerView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ottus.Films.filmList
import com.example.ottus.R

class ActivityForFavorite : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_for_favorite)
        initRecycler(filmList.filter { it.favorite }.toMutableList())

    }

    private fun initRecycler(filmsFavor : MutableList<FilmsItem>) {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewFilmsFavorite)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = FilmsAdapter(LayoutInflater.from(this), filmsFavor)
    }


}