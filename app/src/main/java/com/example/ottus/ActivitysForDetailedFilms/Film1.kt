package com.example.ottus.ActivitysForDetailedFilms

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.core.app.ShareCompat
import com.example.ottus.R

class Film1 : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film1)
        val commentView = findViewById<EditText>(R.id.editViewCommentsLeague)
        val texFromEditText = intent.getCharSequenceExtra("commentsLeague")

        commentView.setText(texFromEditText)

    }

    fun buttonLeagueInvite(view: View) {
        val message = "Пошли со мной в кино на..."

        ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setChooserTitle("Поделиться")
                .setText(message)
                .startChooser()
    }

    fun buttonLeagueBack(view: View) {
        val intentLeagueBack = Intent()
        val textComment = findViewById<EditText>(R.id.editViewCommentsLeague).text

        intentLeagueBack.putExtra("commentsLeagueFromAct", textComment.toString())
        setResult(0, intentLeagueBack)
        finish()




    }
}

