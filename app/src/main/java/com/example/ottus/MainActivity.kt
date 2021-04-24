package com.example.ottus

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.example.ottus.ActivitysForDetailedFilms.Film1
import com.example.ottus.ActivitysForDetailedFilms.Film2
import com.example.ottus.ActivitysForDetailedFilms.Film3
import com.example.ottus.ActivitysForDetailedFilms.Film4

class MainActivity : AppCompatActivity() {

    private var buttonActionList = arrayListOf<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState?.let {
               buttonActionList = savedInstanceState.getIntegerArrayList("1")!!
           }

        buttonActionList.toSet().forEach {
            val i = findViewById<Button>(it)
            i.setTextColor(Color.RED)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putIntegerArrayList("1", buttonActionList)
        super.onSaveInstanceState(outState)
    }

    fun buttonLeague(view: View) {
        val intent = Intent(this, Film1::class.java)
        val buttonLeague = findViewById<Button>(R.id.buttonViewLeague)
        buttonLeague.setTextColor(Color.RED)
        buttonActionList.add(buttonLeague.id)
        Log.e("Main", "film1, ${buttonActionList[0]}, ${buttonLeague.id}")
        startActivity(intent)
    }

    fun buttonMK(view: View) {
        val intent = Intent(this, Film2::class.java)
        val buttonMK = findViewById<Button>(R.id.buttonViewMK)
        buttonMK.setTextColor(Color.RED)
        buttonActionList.add(buttonMK.id)
        Log.e("Main", "film2, ${buttonActionList.forEach { println(it) }}")
        startActivity(intent)
    }

    fun buttonHunter(view: View) {
        val intent = Intent(this, Film3::class.java)
        val buttonViewHunter = findViewById<Button>(R.id.buttonViewHunter)
        buttonViewHunter.setTextColor(Color.RED)
        buttonActionList.add(buttonViewHunter.id)
        Log.e("Main", "film2, ${buttonActionList.forEach { println(it) }}")
        startActivity(intent)
    }

    fun buttonSkyline(view: View) {
        val intent = Intent(this, Film4::class.java)
        val buttonViewSkyline = findViewById<Button>(R.id.buttonViewSkyline)
        buttonViewSkyline.setTextColor(Color.RED)
        buttonActionList.add(buttonViewSkyline.id)
        Log.e("Main", "film2, ${buttonActionList.forEach { println(it) }}")
        startActivity(intent)
    }
}