package com.example.ottus

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.ottus.ActivitysForDetailedFilms.Film1
import com.example.ottus.ActivitysForDetailedFilms.Film2

class MainActivity : AppCompatActivity() {

   private var buttonActionList =  arrayListOf<Int>()


/*   private val commentsLeague = ""
   private val commentsMK = ""
   private val commentsHunter = ""
   private val commentsSkyline = ""*/



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState?.let {

            buttonActionList = savedInstanceState.getIntegerArrayList("1")!!

            buttonActionList.forEach {
                val i = findViewById<Button>(it)
                i.setTextColor(Color.RED)
            }
        }

    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        outState.putIntegerArrayList("1", buttonActionList)
        super.onSaveInstanceState(outState, outPersistentState)
        Log.e("Main", "save")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        this.buttonActionList = savedInstanceState.getIntegerArrayList("1")!!
            Log.e("Main", "rest")
    }

    fun buttonLeague(view: View) {
        val intent = Intent(this, Film1::class.java)
        val buttonLeague = findViewById<Button>(R.id.buttonViewLeague)
        buttonLeague.setTextColor(Color.RED)
        this.buttonActionList.add(buttonLeague.id)

        Toast.makeText(this, "${buttonActionList[0]}", Toast.LENGTH_SHORT).show()
        Log.e("Main", "film1, ${buttonLeague.id}")
        startActivity(intent)

    }
    fun buttonMK(view: View) {
        val intent = Intent(this, Film2::class.java)
        val buttonMK = findViewById<Button>(R.id.buttonViewMK)
        buttonMK.setTextColor(Color.RED)
        buttonActionList.add(buttonMK.id)
        Log.e("Main", "film1, ${buttonMK.id} ${buttonActionList[1]}")
        startActivity(intent)



    }
    fun buttonHunter(view: View) {}
    fun buttonSkyline(view: View) {}
}