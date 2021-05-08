package com.example.ottus.RecyclerView

data class FilmsItem (
        val title: String,
        val subtitle: String,
        val imageFilm: Int,
        var favorite: Boolean = false) {
}