package com.example.ottus.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ottus.Films.filmList
import com.example.ottus.R
import com.example.ottus.RecyclerView.FilmsAdapter
import com.example.ottus.RecyclerView.FilmsItem
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
            adapter = FilmsAdapter(
                    context,
                    LayoutInflater.from(context),
                    filmList
                ) { item, sharedTitle, sharedSubTitle, sharedImage ->
                    listener?.onFilmClick(
                        item,
                        sharedTitle,
                        sharedSubTitle,
                        sharedImage
                    )
                }


            //adapter = FilmsAdapter(LayoutInflater.from(context), filmList){ listener?.onFilmClick(it)}

            // разделение элементов
            // addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

            // анимация добавления - удаления
            itemAnimator = ScaleInRightAnimator()

            //пагинация
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if ((recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition() == filmList.size - 1) {
                        filmList.add(
                            FilmsItem(
                                "${Math.random() * 10}",
                                "pagin",
                                R.drawable.wish_in_24px
                            )
                        )
                        filmList.add(
                            FilmsItem(
                                "${Math.random() * 10}",
                                "pagin",
                                R.drawable.wish_in_24px
                            )
                        )
                        filmList.add(
                            FilmsItem(
                                "${Math.random() * 10}",
                                "pagin",
                                R.drawable.wish_in_24px
                            )
                        )
                        filmList.add(
                            FilmsItem(
                                "${Math.random() * 10}",
                                "pagin",
                                R.drawable.wish_in_24px
                            )
                        )

                        recyclerView.adapter?.notifyItemRangeInserted(
                            filmList.size - 1,
                            filmList.size + 3
                        )
                    }

                }
            })

        }

        val buttonDel = view.findViewById<Button>(R.id.fragment_buttonViewdel)
        buttonDel.setOnClickListener {
            filmList.removeAt(3)
            recyclerViewFilmListFragment.adapter?.notifyItemRemoved(3)
        }

    }

    interface OnFilmsClickListener {
        fun onFilmClick(item: FilmsItem, sharedTitle: View, sharedSubTitle: View, sharedImage: View)

    }
}
