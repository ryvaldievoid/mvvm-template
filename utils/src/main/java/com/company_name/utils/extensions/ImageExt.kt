package com.company_name.utils.extensions

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.widget.ImageView
import java.io.IOException

/*fun ImageView.loadImage(getApplicationContext: Context, url: String?) {
    GlideApp.with(getApplicationContext)
            .load(url)
            .apply { transition(DrawableTransitionOptions.withCrossFade(600)) }
            .into(this)
            .apply { requestOptions() }
}

fun ImageView.loadImage(getApplicationContext: Context, file: Uri?) {
    GlideApp.with(getApplicationContext)
            .load(with(getApplicationContext) { getFile(this, file) })
            .apply(requestOptions())
            .apply { transition(DrawableTransitionOptions.withCrossFade(600)) }
            .into(this)
}

fun ImageView.loadImage(getApplicationContext: Context, file: Drawable?) {
    GlideApp.with(getApplicationContext)
            .load(file)
            .apply { transition(DrawableTransitionOptions.withCrossFade(600)) }
            .apply(requestOptions())
            .into(this)
}

fun ImageView.loadImage(getApplicationContext: Context, file: Bitmap?) {
    GlideApp.with(getApplicationContext)
            .asBitmap()
            .load(file)
            .apply {
                //                transition(DrawableTransitionOptions.withCrossFade(600))
            }
            .apply(requestOptions())
            .into(this)
}

private fun requestOptions(): RequestOptions =
        RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)
                .fallback(R.drawable.bg_camera_cyan_rounded)
                .placeholder(R.drawable.ic_photo_camera)
                .error(R.drawable.ic_photo_camera)
                .skipMemoryCache(false)*/

@Throws(IOException::class)
private fun Context.readBytes(uri: Uri): ByteArray? =
        contentResolver.openInputStream(uri)?.buffered()?.use { it.readBytes() }

//fun Context.getBitmapFromUri(uri: Uri?): Bitmap =
//        BitmapFactory.decodeStream(getInputStream(uri))

//fun Context.getInputStream(uri: Uri?): InputStream? =
//        try {
//            contentResolver.openInputStream(uri ?: getDefaultIcon)
//        } catch (e: FileNotFoundException) {
//            resources.openRawResource(R.raw.new_logo)
//        }

//fun String.getUriFromDrawable(scheme: String = "android.resource",
//                              applicationId: String = BuildConfig.APPLICATION_ID,
//                              resType: String = "drawable"): Uri =
//        try {
//            Uri.parse("$scheme://$applicationId/$resType/$this")
//        } catch (e: Throwable) {
//            Uri.EMPTY
//        }

//val getDefaultIcon: Uri by lazy {
//    try {
//        "ic_launcher".getUriFromDrawable(resType = "mipmap")
//    } catch (e: java.lang.Exception) {
//        Uri.EMPTY
//    }
//}

//fun getUriFromURL(urlString: String?): Uri? =
//        try {
//            Uri.parse(URL(urlString).toURI().toString())
//        } catch (e: MalformedURLException) { // TODO: Malformed | NPE
//            getDefaultIcon
//        } catch (e: NullPointerException) {
//            getDefaultIcon
//        }
//
//fun isImage(file: File?): Boolean = arrayOf("jpg", "png", "gif", "jpeg")
//        .any { file?.name?.toLowerCase()?.endsWith(it) == true }

private fun ImageView.hasNullOrEmptyDrawable(): Boolean {
    val bitmapDrawable: BitmapDrawable? =
            if (drawable is BitmapDrawable) drawable as BitmapDrawable else null

    return bitmapDrawable == null || bitmapDrawable.bitmap == null
}
