package com.example.ottus

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.ottus.ActivitysForDetailedFilms.Film1
import com.example.ottus.ActivitysForDetailedFilms.Film2
import com.example.ottus.ActivitysForDetailedFilms.Film3
import com.example.ottus.ActivitysForDetailedFilms.Film4

class MainActivityforAct : AppCompatActivity() {

    private var buttonActionList = arrayListOf<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

// возвращаем значения переменных после смены состояния активити
        savedInstanceState?.let {
            buttonActionList = savedInstanceState.getIntegerArrayList("1")!!

            //возвращение текста и видимости комментов к лиги
            val commentsLeague = findViewById<TextView>(R.id.textViewCommentsLeague)
            commentsLeague.apply {
                text = savedInstanceState.getString("commentsLeagueSave")
                visibility = savedInstanceState.getInt("visibility")
            }

        }
//восстанавливаем цвета нажатых кнопок
        buttonActionList.toSet().forEach {
            val i = findViewById<Button>(it)
            i.setTextColor(Color.RED)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putIntegerArrayList("1", buttonActionList)
        val commentsLeague = findViewById<TextView>(R.id.textViewCommentsLeague)
        outState.apply {
            putString("commentsLeagueSave", commentsLeague.text.toString())
            putInt("visibility", commentsLeague.visibility)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            0 -> {
                data.let {
                    val textForViewCommentLeague = data?.getStringExtra("commentsLeagueFromAct")
                    val commentsLeague = findViewById<TextView>(R.id.textViewCommentsLeague)
                    commentsLeague.text = textForViewCommentLeague
                    if (textForViewCommentLeague!!.isNotEmpty()) commentsLeague.visibility = View.VISIBLE
                }
            }
            1 -> TODO()
            2 -> TODO()
            3 -> TODO()
        }
    }

    fun buttonLeague(view: View) {
        val intent = Intent(this, Film1::class.java)
        val buttonLeague = findViewById<Button>(R.id.buttonViewLeague)

        buttonLeague.setTextColor(Color.RED)
        buttonActionList.add(buttonLeague.id)
        val commentsLeague = findViewById<TextView>(R.id.textViewCommentsLeague)
        intent.putExtra("commentsLeague", commentsLeague.text)
        startActivityForResult(intent, 0)


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

    override fun onBackPressed() {
        val dialBuilder = AlertDialog.Builder(this)
        val dialI = DialogInterface.OnClickListener { _, res ->
            if (res == -1)  finish() }

        dialBuilder.apply {
            setTitle("Really exit?")
            setNegativeButton("Later", dialI)
          //  setNeutralButton("Later", dialI)
            setPositiveButton("Agree :(", dialI)

            val dialog = dialBuilder.create()
            dialog.show()
        }
        // super.onBackPressed()
    }
}