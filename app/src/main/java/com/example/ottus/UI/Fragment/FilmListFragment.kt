package com.example.ottus.UI.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ottus.Model.Films.moviesTMDB
import com.example.ottus.Model.Network.ResultsItem
import com.example.ottus.Model.Network.TopRatedMovies
import com.example.ottus.R
import com.example.ottus.RecyclerView.FilmsAdapter
import jp.wasabeef.recyclerview.animators.ScaleInRightAnimator

class FilmListFragment : Fragment() {

    var listener: OnFilmsClickListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Log.e("filmlistFr", filmList.forEach { print(it.favorite) }.toString())
        // разворачиваем лайоут всего фрагмента
        return inflater.inflate(R.layout.fragment_film_list_recycler, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //во входящий вью передается лайоут в котором уже ищем конкретные вьюхи
        val recyclerViewFilmListFragment =
            view.findViewById<RecyclerView>(R.id.fragment_recyclerView)


        recyclerViewFilmListFragment.apply {
            //animation при прокрутке
            adapter = FilmsAdapter(context, LayoutInflater.from(context),moviesTMDB)
            { item, sharedTitle, sharedSubTitle, sharedImage ->
                    listener?.onFilmClick(
                        item,
                        sharedTitle,
                        sharedSubTitle,
                        sharedImage
                    )
                }
            Log.e("StartApp", "start Adapter")

            //adapter = FilmsAdapter(LayoutInflater.from(context), filmList){ listener?.onFilmClick(it)}

            // разделение элементов
            // addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

            // анимация добавления - удаления
            itemAnimator = ScaleInRightAnimator()

            //пагинация
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if ((recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition() == moviesTMDB.size - 1) {

                        Log.e("SizeTMDB", "before ${moviesTMDB.size}")
                        TopRatedMovies.getTopRatedMovies()
                        Log.e("SizeTMDB", "after ${moviesTMDB.size}")

                        recyclerView.adapter?.notifyItemRangeInserted(moviesTMDB.size-1, moviesTMDB.size + 20)
                    }

                }
            })

        }

        //удаление элемента с уведомлением адаптера
     /*   val buttonDel = view.findViewById<Button>(R.id.fragment_buttonViewdel)
        buttonDel.setOnClickListener {
            filmList.removeAt(3)
            recyclerViewFilmListFragment.adapter?.notifyItemRemoved(3)
        }*/

    }

    interface OnFilmsClickListener {
        fun onFilmClick(item: ResultsItem, sharedTitle: View, sharedSubTitle: View, sharedImage: View)

    }
}
