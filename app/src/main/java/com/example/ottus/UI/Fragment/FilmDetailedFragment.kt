package com.example.ottus.UI.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.ottus.Model.Network.ResultsItem
import com.example.ottus.R

class FilmDetailedFragment(): Fragment() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

     /*   sharedElementReturnTransition = TransitionInflater.from(context)
            .inflateTransition(R.transition.simple_transition)*/
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_film_detailed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val item = arguments?.getSerializable("bundleDetailed") as ResultsItem

        val imageView = view.findViewById<ImageView>(R.id.fragment_detailed_imageview)
        val title = view.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        val subTitle = view.findViewById<TextView>(R.id.description)

        // Glide - оптимизатор изображений
        context?.let { Glide.with(it).load(item.getPosterPath).into(imageView) }
       //imageView.setImageResource(item.imageFilm)
        title.title = item.title
        subTitle.text = item.overview

        (parentFragment as MainFragment).makeGoneBottomView()

        imageView.setOnClickListener { parentFragmentManager.popBackStack() }




    }

    override fun onStop() {
        super.onStop()

        (parentFragment as MainFragment).makeVisibleBottomView()
    }



    companion object{

        fun newInstance(item: ResultsItem): FilmDetailedFragment{
            val fragment = FilmDetailedFragment()

            val bundle = Bundle()
            bundle.putSerializable("bundleDetailed", item)

            fragment.arguments = bundle

            return fragment

        }
    }


}
