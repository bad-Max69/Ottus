package com.example.ottus.RecyclerView

import android.animation.Animator
import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

class CustomItemAnimator(): DefaultItemAnimator(){
    val removeView: MutableList<RecyclerView.ViewHolder> = ArrayList()

    override fun runPendingAnimations() {
        if (removeView.isNotEmpty()) {
            for (viewHolder in removeView) {
                dispatchRemoveStarting(viewHolder)
                val propertyAnimator = viewHolder.itemView.animate()
                propertyAnimator.setDuration(400).translationX(
                    viewHolder.itemView.x + viewHolder.itemView.width)
                    .alpha(0f)
                    .setListener(object: Animator.AnimatorListener {
                        override fun onAnimationRepeat(p0: Animator?) {  }

                    override fun onAnimationEnd(p0: Animator?) {
                        dispatchRemoveFinished(viewHolder)
                        removeView.remove(viewHolder)
                        dispatchFinishedWhenDone()
                    }

                    override fun onAnimationCancel(p0: Animator?) {

                    }

                    override fun onAnimationStart(p0: Animator?) {
                        dispatchAnimationStarted(viewHolder)
                        dispatchRemoveStarting(viewHolder)
                    }

                }).start()
            }
        }
        super.runPendingAnimations()
    }

    override fun isRunning(): Boolean {
        return super.isRunning() && removeView.isNotEmpty()
    }

    fun dispatchFinishedWhenDone() {
        if (!isRunning) {
            dispatchAnimationsFinished()
        }
    }

    override fun animateRemove(holder: RecyclerView.ViewHolder?): Boolean {
        holder?.let { viewHolder ->
            removeView.add(viewHolder)
        }

        return true
    }
}