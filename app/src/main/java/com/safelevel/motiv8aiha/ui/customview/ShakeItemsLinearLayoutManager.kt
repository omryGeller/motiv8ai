package com.safelevel.motiv8aiha.ui.customview

import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.LinearLayoutManager
import com.safelevel.motiv8aiha.R

class ShakeItemsLinearLayoutManager(context: Context, orientation: Int) : LinearLayoutManager(context,orientation,false) {
    private val itemAnimation = AnimationUtils.loadAnimation(context, R.anim.shake)
    override fun addView(child: View?) {
        super.addView(child)
        child?.startAnimation(itemAnimation)
    }
}