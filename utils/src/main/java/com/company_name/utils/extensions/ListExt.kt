package com.company_name.utils.extensions

import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.setupLinearLayoutManager(orientation: Int, isReversed: Boolean) {
    layoutManager = LinearLayoutManager(
            context,
            if (orientation == 1) LinearLayoutManager.HORIZONTAL else LinearLayoutManager.VERTICAL,
            isReversed)
    setHasFixedSize(true)
    itemAnimator = DefaultItemAnimator()
    setItemViewCacheSize(30)
    isDrawingCacheEnabled = true
    drawingCacheQuality = View.DRAWING_CACHE_QUALITY_HIGH
}

fun RecyclerView.setupGridLayoutManager(span: Int, orientation: Int, isReversed: Boolean) {
    layoutManager = GridLayoutManager(context, span)
    setHasFixedSize(true)
    itemAnimator = DefaultItemAnimator()
    setItemViewCacheSize(30)
    isDrawingCacheEnabled = true
    drawingCacheQuality = View.DRAWING_CACHE_QUALITY_HIGH
}