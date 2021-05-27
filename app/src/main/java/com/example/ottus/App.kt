package com.example.ottus

import android.app.Application
import android.util.Log

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        instance = this

        //TopRatedMoviesInterractor.getTopRatedMovies()
        Log.e("Live", "APPPPP get top rated movies")



    }


    companion object {
        var instance: App? = null
            private set
    }

}