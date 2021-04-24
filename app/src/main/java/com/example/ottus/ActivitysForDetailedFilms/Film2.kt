package com.example.ottus.ActivitysForDetailedFilms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ShareCompat
import com.example.ottus.R

class Film2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film2)
    }

    fun buttonHunterInvite(view: View) {
        val message = "Пошли со мной в кино на..."

        ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setChooserTitle("Поделиться")
                .setText(message)
                .startChooser()
    }
}