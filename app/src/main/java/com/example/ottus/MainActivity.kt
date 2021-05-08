package com.example.ottus

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ottus.Films.filmList
import com.example.ottus.RecyclerView.ActivityForFavorite
import com.example.ottus.RecyclerView.CustomItemAnimator
import com.example.ottus.RecyclerView.FilmsAdapter
import com.example.ottus.RecyclerView.FilmsItem

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_for_recycler)

        initRecycler()
    }

    private fun initRecycler(){
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewFilms)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = FilmsAdapter(LayoutInflater.from(this),  filmList)

        //кнопка добавления элементов
        findViewById<View>(R.id.buttonViewAdd).setOnClickListener(){
            filmList.add(2 , FilmsItem("new Film", "newSub", R.drawable.ic_launcher_foreground))
            recyclerView.adapter?.notifyItemInserted(3)
        }
        //кнопка удаления элементов
        findViewById<View>(R.id.buttonViewDel).setOnClickListener(){
            filmList.removeAt(3)
            recyclerView.adapter?.notifyItemRemoved(3)
        }

        //кнопка перехода на активити со списком избранных фильмов
        findViewById<View>(R.id.favorite).setOnClickListener(){
            val intent = Intent(this, ActivityForFavorite::class.java)
            startActivity(intent)

        }

        //Пагинация - подгрузка новых данных при достижении заданного конченого элемента
        recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if ((recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition() == filmList.size -1) {
                    filmList.add(FilmsItem("${Math.random()}", "pagin", R.drawable.wish_in_24px))
                    filmList.add(FilmsItem("${Math.random()}", "pagin", R.drawable.wish_in_24px))
                    filmList.add(FilmsItem("${Math.random()}", "pagin", R.drawable.wish_in_24px))
                    filmList.add(FilmsItem("${Math.random()}", "pagin", R.drawable.wish_in_24px))

                    recyclerView.adapter?.notifyItemRangeInserted(filmList.size - 1, filmList.size + 3)
                }


            }
        })
        // разделение элементов стандартной полоской
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        val myAnima = CustomItemAnimator()
        recyclerView.itemAnimator =myAnima




        }

}
