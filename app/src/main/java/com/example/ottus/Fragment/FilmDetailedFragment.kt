package com.example.ottus.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.ottus.R
import com.example.ottus.RecyclerView.FilmsItem

class FilmDetailedFragment(): Fragment() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_film_detailed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val item = arguments?.getSerializable("bundleDetailed") as FilmsItem

        val imageView = view.findViewById<ImageView>(R.id.fragment_detailed_imageview)
        val title = view.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        val subTitle = view.findViewById<TextView>(R.id.description)



        imageView.setImageResource(item.imageFilm)
        title.title = item.title
        subTitle.text = item.subtitle
    }

    companion object{

        fun newInstance(item: FilmsItem): FilmDetailedFragment{
            val fragment = FilmDetailedFragment()

            val bundle = Bundle()
            bundle.putSerializable("bundleDetailed", item)

            fragment.arguments = bundle

            return fragment

        }
    }


}
