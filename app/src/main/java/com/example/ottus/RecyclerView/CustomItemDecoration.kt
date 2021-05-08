package com.example.ottus.RecyclerView

import android.animation.Animator
import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

class CustomItemAnimator(): DefaultItemAnimator(){
    override fun animateAdd(holder: RecyclerView.ViewHolder?): Boolean {
        Log.e("anim", "add")
        return super.animateAdd(holder)
    }



    override fun animateRemove(holder: RecyclerView.ViewHolder?): Boolean {
       Log.e("anim", "remove")
        return super.animateRemove(holder)
    }
}