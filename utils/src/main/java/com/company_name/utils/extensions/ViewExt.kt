package com.company_name.utils.extensions

import android.graphics.Typeface
import android.text.TextUtils
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.company_name.utils.Helper.Const.SERVER_ERROR_MESSAGE_DEFAULT
import com.company_name.utils.Helper.Const.SNACKBAR_TIMER_SHOWING_DEFAULT
import com.company_name.utils.R

fun View.visible(

) {
    visibility = View.VISIBLE
}

fun View.gone(

) {
    visibility = View.GONE
}

fun View.invisible(

) {
    visibility = View.INVISIBLE
}

fun View.showSnackbarWithCustomColor(
    message: String,
    textColor: Int, backgroundColor: Int,
    duration: Int = 5000
) {
    val finalMessage = if (TextUtils.isEmpty(message)) {
        SERVER_ERROR_MESSAGE_DEFAULT
    } else {
        message
    }

    val finalDuration = if (duration == 0) {
        SNACKBAR_TIMER_SHOWING_DEFAULT
    } else {
        duration
    }

    val finalTextColor = if (textColor == 0) {
        ContextCompat.getColor(this.context, R.color.mainWhite)
    } else {
        textColor
    }

    val finalBackgroundColor = if (textColor == 0) {
        ContextCompat.getColor(this.context, R.color.greyBackgroundDefault)
    } else {
        backgroundColor
    }

    val snackView = Snackbar.make(this, finalMessage, finalDuration)
    snackView.setActionTextColor(finalTextColor)
    snackView.view.setBackgroundColor(finalBackgroundColor)
    snackView.show()
}

fun View.showSnackbarDefault(
    message: String,
    duration: Int = 5000
) {
    val finalMessage = if (TextUtils.isEmpty(message)) {
        SERVER_ERROR_MESSAGE_DEFAULT
    } else {
        message
    }

    val finalDuration = if (duration == 0) {
        SNACKBAR_TIMER_SHOWING_DEFAULT
    } else {
        duration
    }

    Snackbar.make(this, finalMessage, finalDuration).show()
}

fun View.setCustomFont(
    fontName: String
): Typeface = Typeface
    .createFromAsset(this.context.assets, "fonts/$fontName")


fun RecyclerView.verticalListStyle(

) {
    layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    setHasFixedSize(true)
    itemAnimator = DefaultItemAnimator()
    setItemViewCacheSize(30)
    isDrawingCacheEnabled = true
    drawingCacheQuality = View.DRAWING_CACHE_QUALITY_HIGH
}

fun RecyclerView.horizontalListStyle(

) {
    layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    setHasFixedSize(true)
    itemAnimator = DefaultItemAnimator()
    setItemViewCacheSize(30)
    isDrawingCacheEnabled = true
    drawingCacheQuality = View.DRAWING_CACHE_QUALITY_HIGH
}