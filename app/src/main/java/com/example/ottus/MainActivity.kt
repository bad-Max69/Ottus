package com.example.ottus

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.ottus.UI.Fragment.MainFragment


class MainActivity :
    AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_for_fragment)


        // makeCurrentFragment(SplashDownloadFragment())
        Log.e("Live", "act on creat film list frag")
        makeCurrentFragment(MainFragment())

    }


    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_Root, fragment)
            .commit()

    }

    override fun onBackPressed() {
        val fm = supportFragmentManager
        for (frag in fm.fragments) {
            if (frag.isVisible) {
                val childFm = frag.childFragmentManager
                if (childFm.backStackEntryCount > 0) {
                    childFm.popBackStack()
                } else {
                    val dialBuilder = AlertDialog.Builder(this)

                    val dialI = DialogInterface.OnClickListener { _, res ->
                        if (res == -1) finish()
                    }

                    dialBuilder.apply {
                        setTitle("Выйти из приложения?")
                        setNegativeButton("Нет, остаюсь", dialI)
                        //  setNeutralButton("Later", dialI)
                        setPositiveButton("Да :(", dialI)

                        val dialog = dialBuilder.create()
                        dialog.show()
                    }
                }

            }
        }
    }
}





