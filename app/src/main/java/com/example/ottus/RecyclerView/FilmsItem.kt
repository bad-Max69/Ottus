package com.example.ottus.RecyclerView

import java.io.Serializable

data class FilmsItem (
        val title: String,
        val subtitle: String,
        val imageFilm: Int,
        var favorite: Boolean = false) : Serializable {
}