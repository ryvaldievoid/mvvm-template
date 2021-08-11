package com.company_name.utils

import android.annotation.SuppressLint
import android.view.View
import android.webkit.WebView
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.PrecomputedTextCompat
import androidx.core.widget.TextViewCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

object Bindings {

    @BindingAdapter("app:progressColor")
    @JvmStatic
    fun setProgressColor(loader: ProgressBar?, color: Int) {
        loader?.indeterminateDrawable?.setColorFilter(color, android.graphics.PorterDuff.Mode.SRC_IN)
    }

    @BindingAdapter("app:activeColor")
    @JvmStatic
    fun setBackgroundColorItemList(view: View, activeColor: Int?) {
        view.setBackgroundColor(activeColor ?: 0)
    }

    @BindingAdapter("app:currencyValue")
    @JvmStatic
    fun setCurrenyFormatToRupiah(textView: TextView, currencyValue: Double?) {
        textView.text = Helper.Func.currencyFormatToRupiah(currencyValue
                ?: Helper.Const.CURRENCY_VALUE_DEFAULT)
    }

    @SuppressLint("PrivateResource")
    @BindingAdapter("app:imageUrl")
    @JvmStatic
    fun setImageUrl(view: ImageView, imageUrl: String?) {
        Glide.with(view.context)
                .load(Helper.Const.BASE_IMAGE_URL_MOVIE_DB + imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade(
                        Helper.Const.GLIDE_FADE_ANIMATION_TIME_DEFAULT
                ))
                .into(view)
                .apply {
                    RequestOptions().fallback(R.color.material_grey_300)
                }
    }

    @SuppressLint("SetJavaScriptEnabled")
    @BindingAdapter("app:webviewContent", "app:webviewTextSize")
    @JvmStatic
    fun setClearWebviewContent(webView: WebView, webviewContent: String?, webviewTextSize: Int?) {
        if (webviewContent != null) {
            webView.loadDataWithBaseURL(null, Helper.Func
                    .setClearWebviewContent(webviewContent), "text/html",
                    "utf-8", null)
            webView.settings.javaScriptEnabled = true
            webView.settings.defaultFontSize = if (webviewTextSize == null || webviewTextSize == 0) {
                Helper.Const.WEBVIEW_TEXT_SIZE_DEFAULT
            } else {
                webviewTextSize
            }
        }
    }

//    @BindingAdapter("app:recyclerData", "app:orientationList")
//    @JvmStatic
//    fun <T> setupRecyclerviewDatas(recyclerView: RecyclerView, recyclerData: MutableLiveData<List<T>>,
//                                   orientationList: Int?) {
//        try {
//            if (recyclerView.adapter is GitsBindableAdapter<*>) {
//                if (orientationList == 1) recyclerView.horizontalListStyle() else
//                    recyclerView.verticalListStyle()
//                (recyclerView.adapter as GitsBindableAdapter<T>)
//                        .setRecyclerViewData(recyclerData.value!!)
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }

    @BindingAdapter("app:textHtmlContent")
    @JvmStatic
    fun setHtmlTextContent(textView: TextView, text: String?) {
        if (text != null) {
            // textView.text = Jsoup.parse(text).text()
        }
    }

    @BindingAdapter("app:textAsync", "app:textSizes", requireAll = false)
    @JvmStatic
    fun setTextAsync(textView: TextView, textAsync: String?, textSizes: Int?) {
        if (textSizes != null) {
            textView.textSize = 14.toFloat()
        }

        val params = TextViewCompat.getTextMetricsParams(textView)
        (textView as AppCompatTextView).setTextFuture(
                PrecomputedTextCompat.getTextFuture(textAsync.toString(), params, null))
    }
}